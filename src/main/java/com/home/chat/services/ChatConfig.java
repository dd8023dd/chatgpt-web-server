package com.home.chat.services;

import com.home.chat.dao.TbApikeyDAO;
import com.home.chat.dao.TbUserKeyDAO;
import com.home.chat.pojo.Constant;
import com.home.chat.pojo.entity.TbApikeyEntity;
import com.home.chat.pojo.query.TbApikeyQuery;
import com.home.chat.redis.RedisDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Deacription TODO
 * @Author Zhangmj
 * @Date 2023/4/10 17:20
 * @Version 1.0
 **/
@Service
@Slf4j
public class ChatConfig {

    @Autowired
    TbApikeyDAO tbApikeyDAO;

    @Autowired
    TbUserKeyDAO tbUserKeyDAO;

    @Autowired
    RedisDataHelper redisDataHelper;


    @PostConstruct
    public void initOutConfig() {
        loadingOutConfig();
    }

    private void loadingOutConfig(){
        log.info("################## 开始加载配置 #####################");
        List<TbApikeyEntity> tbApikeyEntities = tbApikeyDAO.queryForList(new TbApikeyQuery());
        Map<String, Object> apikeyMap = tbApikeyEntities.stream().collect(Collectors.toMap(TbApikeyEntity::getApiKey, Function.identity()));
        redisDataHelper.setKey(Constant.API_KEY_,apikeyMap);
        log.info("################## 结束加载配置 #####################");
    }

}
