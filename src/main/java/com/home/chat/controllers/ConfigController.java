package com.home.chat.controllers;

import com.home.chat.domain.ChatWebConfig;
import com.home.chat.domain.common.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@ResponseBody
@RequestMapping("api/configs")
@RequiredArgsConstructor
public class ConfigController {

    private final ChatWebConfig config;

    @GetMapping
    public String getConfig() {
        return config.getContent();
    }

    @GetMapping("/content/{authToken}/{content}")
    public String replaceContent(@PathVariable String authToken, @PathVariable String content) throws BusinessException {
        if (Objects.equals(config.getAuth(), authToken)) {
            config.setContent(content);
            return "设置成功，请刷新 ChatGPT 页面";
        }

        return "auth token 不正确";
    }


    @GetMapping("/token/{authToken}/{newToken}")
    public String replaceToken(@PathVariable String authToken, @PathVariable String newToken) throws BusinessException {
        if (Objects.equals(config.getAuth(), authToken)) {
            config.setAuth(newToken);
            return "设置成功新的 token 是" + newToken;
        }

        return "auth token 不正确";
    }

    @GetMapping("/free/{authToken}/{key}")
    public String replaceKey(@PathVariable String authToken, @PathVariable String key) throws BusinessException {
        if (Objects.equals(config.getAuth(), authToken)) {
            config.setFreeApiKey(key);
            return "设置成功";
        }

        return "auth token 不正确";
    }

    @GetMapping("/reset/{authToken}")
    public String reset(@PathVariable String authToken) {
        if (Objects.equals(config.getAuth(), authToken)) {
            config.setFreeApiKey(null);
            return "重置成功";
        }
        return "auth token 不正确";
    }

}
