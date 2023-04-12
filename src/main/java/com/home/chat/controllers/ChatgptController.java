package com.home.chat.controllers;

import cn.hutool.json.JSONObject;
import com.home.chat.controllers.request.Message;
import com.home.chat.controllers.request.QueryUserBalanceRequest;
import com.home.chat.controllers.response.QueryBalanceResponse;
import com.home.chat.controllers.response.QueryUserBalanceResponse;
import com.home.chat.pojo.Constant;
import com.home.chat.services.ChatGPTService;
import com.home.chat.redis.RedisDataHelper;
import com.home.chat.utils.SensitiveWordsUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("api/chat")
@RequiredArgsConstructor
@ResponseBody
public class ChatgptController {
    private final ChatGPTService gptService;

    @Autowired
    RedisDataHelper redisDataHelper;

    @GetMapping("/balances")
    public QueryBalanceResponse queryBalanceResponse(@RequestParam(required = false) String apiKey) {
        return gptService.creditQuery(apiKey);
    }

    @PostMapping("send")
    public void stream(@RequestBody Message message,
                       HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        if(!message.getMessage().isEmpty()){
            //敏感词检测
            for (String content : message.getMessage()){
                List<String> filter = SensitiveWordsUtils.checkBadwords(content);
                if(!filter.isEmpty()){
                    outputStream.write("请勿输入敏感词汇！超过5次将进行封号处理！".getBytes());
                    Object scanTimes = redisDataHelper.getValue(Constant.SENSITIVE_WORDS_SCAN, message.getApiKey());
                    redisDataHelper.hput(Constant.SENSITIVE_WORDS_SCAN, message.getApiKey(),scanTimes != null ? (int)scanTimes + 1 : 1);
                    outputStream.flush();
                    outputStream.close();
                    return;
                }
            }
        }

        response.setContentType("application/octet-stream");
        gptService.sendResponse(message, (str) -> {
            try {
                if (!"END".equals(str)) {
                    outputStream.write(str.getBytes());
                    outputStream.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        outputStream.close();
    }

    /**
     *
     * @param request
     * @return [request]
     * @author zhangmj
     * @date 2023/4/10 16:55
     * @Description userBalances 查询剩余使用量
     * @version 1.0
     * @since JDK 1.8
     **/
    @PostMapping("/userBalances")
    public JSONObject userBalances(@RequestBody QueryUserBalanceRequest request){
        JSONObject jo = new JSONObject();
        if(StringUtils.isBlank(request.getKey())){
            QueryUserBalanceResponse response = new QueryUserBalanceResponse();
            response.setMessage("请输入UserKey进行查询");
            jo.set("data",response);
            jo.set("status","Fail");
            return jo;
        }
        jo.set("status","Success");
        jo.set("data",gptService.queryUserBalance(request));
        return jo;
    }
}
