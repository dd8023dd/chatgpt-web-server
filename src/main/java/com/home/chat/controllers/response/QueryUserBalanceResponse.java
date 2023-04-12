package com.home.chat.controllers.response;

import lombok.*;

/**
 * @Deacription TODO
 * @Author Zhangmj
 * @Date 2023/4/10 16:43
 * @Version 1.0
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryUserBalanceResponse  {



    //过期时间
    private Long expireDate;

    //剩余次数
    private Long remainingCount;

    private String message;


}
