package com.hugh.stream.UserPredicate;

import com.hugh.stream.common.TestDataBuilder;
import com.hugh.stream.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserFilter {
    /**
     * 用户筛选
     *
     * @param users
     * @param userPredicate
     * @return
     */
    private static List<User> filterUsers(List<User> users, UserPredicate userPredicate) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (userPredicate.test(user)) {
                result.add(user);
            }
        }
        return result;

    }

    public static void main(String[] args) {
        /**
         * 每加一次筛选条件 需要创建一个UserPredicate 的实现类
         */
        List<User> users = TestDataBuilder.buildList();
        List<User> users1 = filterUsers(users, new UserAgePredicate());
        users1 = filterUsers(users1, new UserMoneyPredicate());
        System.out.println(users1);
        System.err.println("******************************************************");
        /**
         *  使用匿名类处理
         */
        List<User> users2 = filterUsers(users, new UserPredicate() {
            @Override
            public boolean test(User user) {
                return user.getMoney().compareTo(new BigDecimal(10)) == 1
                        && user.getAge() > 10;
            }
        });
        System.out.println(users2);
        System.err.println("******************************************************");
        /**
         * 使用lambda
         */
        List<User> users3 = filterUsers(users, user -> user.getMoney().compareTo(new BigDecimal(10)) == 1 && user.getAge() > 10);
        System.out.println(users3);

    }
}
