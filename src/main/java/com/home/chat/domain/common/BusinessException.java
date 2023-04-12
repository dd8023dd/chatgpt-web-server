package com.home.chat.domain.common;


public class BusinessException extends Exception {
    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(ErrorCode errorCode) {
        this.message = errorCode.getMsgCn();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        this.message = errorCode.getMsgCn() + "[" + message + "]";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
