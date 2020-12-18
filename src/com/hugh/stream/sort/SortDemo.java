package com.hugh.stream.sort;

import com.hugh.stream.common.TestDataBuilder;
import com.hugh.stream.model.User;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * 排序
 */
public class SortDemo {


    public static void main(String[] args) {
        List<User> users = TestDataBuilder.buildList();
        /**
         * 按年纪排序
         * 匿名类
         */
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        System.out.println(users);
        System.err.println("********************************************");
        /**
         * lambda
         */
        users.sort(comparing(User::getMoney));
        users.sort((o1, o2) -> o1.getAge().compareTo(o2.getAge()));
        System.out.println(users);
        /**
         * 逆序
         */
        users.sort(comparing(User::getAge).reversed());
        /**
         * 比较器链
         */
        users.sort(comparing(User::getAge).thenComparing(User::getMoney));

        User user = new User();
        


    }
}
