package com.home.chat.domain;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "chat")
public class ChatWebConfig {
    private String content = "【东东的AI小站】";
    private String auth = "dchatbot";
    private String freeApiKey;
    private String notAuthContent;
}
