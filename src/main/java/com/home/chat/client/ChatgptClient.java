package com.home.chat.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name = "chatgpt-client", url = "https://api.openai.com/v1/chat/completions")
public interface ChatgptClient {

    @PostMapping()
    void send(@RequestBody HashMap<Object, Object> params, @RequestHeader Map<String,String> header);

}
