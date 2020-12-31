package com.hugh.stream.test;

import com.hugh.stream.model.User;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        User user = new User();
        Optional<User> optional = Optional.of(user);
        System.out.println(optional.get());
    }
}
