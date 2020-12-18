package com.hugh.stream.UserPredicate;

import com.hugh.stream.model.User;

/**
 * 年纪筛选
 */
public class UserAgePredicate implements UserPredicate {
    @Override
    public boolean test(User user) {
        return user.getAge() > 10;
    }
}
