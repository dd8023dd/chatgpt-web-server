package com.home.chat.domain.common;

import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {
    private List<T> data;
    private Long total;
    private boolean success;

    public PageVo(List<T> data, long total) {
        this.success = true;
        this.data = data;
        this.total = total;
    }

    public static <T> PageVo<T> build(List<T> datas) {
        return new PageVo<>(datas, datas.size());
    }
}
