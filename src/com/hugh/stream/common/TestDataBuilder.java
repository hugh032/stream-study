package com.hugh.stream.common;

import com.hugh.stream.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * demo数据
 */
public class TestDataBuilder {
    public static List<User> buildList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "张三", 20, new BigDecimal(100), 1);
        User user2 = new User(2, "张四", 21, new BigDecimal(5), 1);
        User user3 = new User(3, "李鹏飞", 18, new BigDecimal(20), 1);
        User user4 = new User(4, "王九六", 5, new BigDecimal(1), 1);
        User user5 = new User(5, "刘德花", 2, new BigDecimal(7), 1);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        return userList;
    }


    public static void main(String[] args) {
        List<User> users = buildList();
        /**
         * anyMatch表示，判断的条件里，任意一个元素成功，返回true
         */
        boolean anyMatch = users.stream().anyMatch(user -> user.getId().equals(20));
        System.out.println("anyMatch===" + anyMatch);
        /**
         * allMatch表示，判断条件里的元素，所有的都是，返回true
         */
        boolean allMatch = users.stream().allMatch(user -> user.getSex().equals(1));
        System.out.println("allMatch===" + allMatch);
        /**
         * noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
         */
        boolean noneMatch = users.stream().noneMatch(user -> user.getSex().equals(0));
        System.out.println("noneMatch===" + noneMatch);

        List<User> collect = users.parallelStream().filter(user -> user.getAge() != null && user.getAge() < 21).collect(Collectors.toList());
        System.out.println(collect.toString());


    }


}
