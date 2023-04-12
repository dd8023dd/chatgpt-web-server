package com.home.chat.services;

import com.home.chat.pojo.Constant;
import com.home.chat.redis.RedisDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @Deacription TODO
 * @Author Zhangmj
 * @Date 2023/4/11 10:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class SensitiveWordsConfig {



    @Autowired
    RedisDataHelper redisDataHelper;


    @PostConstruct
    public void iniSensitiveWords() {
        loadingSensitiveWords();
    }

    private void loadingSensitiveWords(){
        log.info("################## 开始加载敏感词配置 #####################");
        //初始化敏感词过滤器，用于记录触发敏感词次数。
        HashMap<String,Object> scanTimes = new HashMap<>();
        redisDataHelper.setKey(Constant.SENSITIVE_WORDS_SCAN,scanTimes);
        log.info("################## 结束加载敏感词配置#####################");
    }

}
