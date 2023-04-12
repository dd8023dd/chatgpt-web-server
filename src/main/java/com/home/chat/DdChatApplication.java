package com.home.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan({"com.home.chat.**.dao"})
@ComponentScan("com.home.chat.*")
public class DdChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdChatApplication.class, args);
    }

}
