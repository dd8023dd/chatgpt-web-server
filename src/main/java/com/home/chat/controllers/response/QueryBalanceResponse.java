package com.home.chat.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryBalanceResponse {
    private String balances;

    public boolean willExpired() {
        BigDecimal balance = Optional.ofNullable(balances).map(BigDecimal::new).orElse(BigDecimal.ZERO);
        return balance.compareTo(new BigDecimal("0.2")) < 0;
    }
}
