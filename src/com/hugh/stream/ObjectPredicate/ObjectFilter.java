package com.hugh.stream.ObjectPredicate;

import com.hugh.stream.common.TestDataBuilder;
import com.hugh.stream.model.User;

import java.util.ArrayList;
import java.util.List;

public class ObjectFilter {
    /**
     * 过滤
     * @param list
     * @param op
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list,ObjectPredicate<T> op) {
        List<T> result = new ArrayList<>();
        for (T t:list) {
            if (op.test(t)) {
                result.add(t);
            }
        }
        return result;

    }

    public static void main(String[] args) {
        List<User> users = TestDataBuilder.buildList();
        List<User> user1 = filter(users, new ObjectPredicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getAge() > 10;
            }
        });
        System.out.println(user1);
        System.err.println("***************************************************");
        List<User> user2 = filter(users, user -> user.getAge() > 10);
        System.out.println(user2);
    }
}
