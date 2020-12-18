package com.hugh.stream.UserPredicate;

import com.hugh.stream.model.User;

import java.math.BigDecimal;

/**
 * 账户余额筛选
 */
public class UserMoneyPredicate implements UserPredicate {
    @Override
    public boolean test(User user) {
        return user.getMoney().compareTo(new BigDecimal(10)) == 1;
    }
}
