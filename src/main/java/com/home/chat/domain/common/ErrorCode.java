package com.home.chat.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    UPLOAD_FAILED("上传失败", ""),
    NOT_IMAGE_FORMAT("图片格式错误", "");

    private final String msgCn;
    private final String msgEn;

    public static Supplier<BusinessException> throwBizException(ErrorCode errorCode) {
        return () -> new BusinessException(errorCode);
    }
}
