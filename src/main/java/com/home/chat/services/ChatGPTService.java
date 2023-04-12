package com.home.chat.services;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.home.chat.controllers.request.Message;
import com.home.chat.controllers.request.QueryUserBalanceRequest;
import com.home.chat.controllers.response.QueryBalanceResponse;
import com.home.chat.controllers.response.QueryUserBalanceResponse;
import com.home.chat.dao.TbApikeyDAO;
import com.home.chat.dao.TbUserKeyDAO;
import com.home.chat.domain.OpenAiConfig;
import com.home.chat.domain.ChatWebConfig;
import com.home.chat.pojo.entity.TbApikeyEntity;
import com.home.chat.pojo.entity.TbUserKeyEntity;
import com.home.chat.pojo.query.TbApikeyQuery;
import com.home.chat.pojo.query.TbUserKeyQuery;
import com.home.chat.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatGPTService {

    private final OpenAiConfig openAiConfig;
    private final ChatWebConfig xinQiuConfig;

    @Autowired
    TbApikeyDAO tbApikeyDAO;

    @Autowired
    TbUserKeyDAO tbUserKeyDAO;


    public QueryBalanceResponse creditQuery(String key) {

        String apikey = openAiConfig.getApiKey();
        if (StrUtil.isNotBlank(key)) {
            apikey = key;
        }
        String result = HttpRequest.get(openAiConfig.getCreditApi())
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.AUTHORIZATION, "Bearer " + apikey)
                .execute().body();
        if (result.contains("server_error")) {
            throw new RuntimeException("请求ChatGPT官方服务器出错");
        }
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 返回结果
        return QueryBalanceResponse.builder()
                .balances(jsonObject.getStr("total_available"))
                .build();
    }

    private void genImage(Message message, String key, Consumer<String> send) {
        // 请求参数
        Map<String, String> userMessage = MapUtil.of(
                "size", "512x512"
        );
        userMessage.put("prompt", message.getMessage().get(0));

        // 调用接口
        String result = HttpRequest.post(openAiConfig.getImageApi())
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.AUTHORIZATION, "Bearer " + key)
                .body(JSONUtil.toJsonStr(userMessage))
                .execute().body();
        // 正则匹配出结果
        Pattern p = Pattern.compile("\"url\": \"(.*?)\"");
        Matcher m = p.matcher(result);
        if (m.find()) {
            send.accept(m.group(1));
            //扣除次数
            tbUserKeyDAO.useOnece(3,message.getApiKey());
        } else {
            send.accept("图片生成失败！");
        }
    }

    public void sendResponse(Message message, Consumer<String> send) throws IOException {
        TbUserKeyQuery userKeyQuery = new TbUserKeyQuery();
        userKeyQuery.setUserKey(message.getApiKey());
        TbUserKeyEntity tbUserKeyEntity = tbUserKeyDAO.queryForObject(userKeyQuery);
        if(StringUtils.isBlank(message.getApiKey()) || tbUserKeyEntity == null || !tbUserKeyEntity.getValidStatus().equals("1")){
            send.accept("user key无效,请在页面左下角设置正确的key！");
            return;
        }
        if(tbUserKeyEntity.getRemainingCount() <= 0){
            send.accept("user key次数已耗尽！");
            return;
        }
        TbApikeyQuery apikeyQuery = new TbApikeyQuery();
        apikeyQuery.setValidStatus("1");
        apikeyQuery.setEndDate(DateUtil.getCurrDate());
        apikeyQuery.setOrder("balance desc,use_times asc");
        TbApikeyEntity tbApikeyEntity = tbApikeyDAO.queryForObject(apikeyQuery);
        String key = tbApikeyEntity.getApiKey();
        tbApikeyDAO.useOnece(key);
        if (Objects.equals(message.getType(), Message.MessageType.IMAGE)) {
            genImage(message, key, send);
            return;
        }

        // 构建对话参数
        List<Map<String, String>> messages = message.getMessage().stream().map(msg -> {
            Map<String, String> userMessage = MapUtil.of(
                    "role", "user"
            );
            userMessage.put("content", msg);
            return userMessage;
        }).collect(Collectors.toList());


        // 构建请求参数
        HashMap<Object, Object> params = new HashMap<>();
        params.put("stream", true);
        params.put("model", openAiConfig.getModel());
        params.put("messages", messages);

        // 调用接口
        HttpResponse result;
        try {
            result = HttpRequest.post(openAiConfig.getOpenaiApi())
                    .header(Header.CONTENT_TYPE, "application/json")
                    .header(Header.AUTHORIZATION, "Bearer " + key)
                    .body(JSONUtil.toJsonStr(params))
                    .executeAsync();
        } catch (Exception e) {
            send.accept(String.join("", "出错了", e.getMessage()));
            send.accept("END");
            return;
        }
        // 处理数据
        String line;
        assert result != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(result.bodyStream()));
        boolean printErrorMsg = false;
        StringBuilder errMsg = new StringBuilder();
        Boolean userflag = false;
        while ((line = reader.readLine()) != null) {
            String msgResult = UnicodeUtil.toString(line);

            // 正则匹配错误信息
            if (msgResult.contains("\"error\":")) {
                printErrorMsg = true;
            }
            // 如果出错，打印错误信息
            if (printErrorMsg) {
                errMsg.append(msgResult);
            } else if (msgResult.contains("content")) {
                String data = JSONUtil.parseObj(line.substring(5)).getByPath("choices[0].delta.content").toString();
                send.accept(data);
                //扣除次数
                userflag = true;
            }
        }
        if(userflag){
            //这里可以调整消耗次数
            tbUserKeyDAO.useOnece(message.getMessage().size() > 1 ? message.getMessage().size()/2 : 1,message.getApiKey());
        }
        // 关闭流
        reader.close();
        // 如果出错，抛出异常
        if (printErrorMsg) {
            send.accept(errMsg.toString());
            send.accept("END");
        }
        send.accept("END");
    }

    public QueryUserBalanceResponse queryUserBalance(QueryUserBalanceRequest request){
        QueryUserBalanceResponse response = new QueryUserBalanceResponse();
        TbUserKeyQuery query = new TbUserKeyQuery();
        query.setUserKey(request.getKey());
        TbUserKeyEntity tbUserKeyEntity = tbUserKeyDAO.queryForObject(query);
        if(tbUserKeyEntity == null){
            return response;
        }
        response.setExpireDate(tbUserKeyEntity.getExpireDate());
        response.setRemainingCount(tbUserKeyEntity.getRemainingCount());
        return response;
    }
}
