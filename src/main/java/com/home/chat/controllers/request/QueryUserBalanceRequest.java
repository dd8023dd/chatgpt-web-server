package com.home.chat.controllers.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Deacription TODO
 * @Author Zhangmj
 * @Date 2023/4/10 16:54
 * @Version 1.0
 **/
@Setter
@Getter
@Data
public class QueryUserBalanceRequest {

    private String key;

}
