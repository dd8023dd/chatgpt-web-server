package com.home.chat.controllers.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Message {
    private String apiKey;
    private List<String> message;
    private int version;
    private MessageType type;


    public enum MessageType {
        TEXT, IMAGE
    }
}
