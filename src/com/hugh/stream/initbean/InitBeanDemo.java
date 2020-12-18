package com.hugh.stream.initbean;

import com.hugh.stream.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造函数引用  创建对象demo
 */
public class InitBeanDemo {

    /**
     * 批量创建兑现对象
     *
     * @param names
     * @param function
     * @return
     */
    public static List<User> map(List<String> names, Function<String, User> function) {
        ArrayList<User> users = new ArrayList<>();
        for (String name : names) {
            users.add(function.apply(name));
        }
        return users;
    }

    public static void main(String[] args) {
        /**
         * 引用默认的构造函数User()创建对象
         */
        Supplier<User> c1 = User::new;
        User user_c1 = c1.get();
        // 这就等价于
        Supplier<User> c2 = () -> new User();
        User user_c2 = c2.get();
        System.out.println("Supplier...." + user_c1);
        System.out.println("Supplier...." + user_c2);
        /**
         * 如果你的构造函数的签名是User(String name) ，那么可以使用Function接口
         */
        Function<String, User> c3 = User::new;
        User user_c3 = c3.apply("张无忌");
        System.out.println(user_c3);
        Function<String, User> c4 = (String s) -> new User(s);
        User user_c4 = c4.apply("敏敏特穆尔");
        System.out.println(user_c4);
        // 批量创建
        List<String> strings = Arrays.asList("白眉鹰王", "青翼蝠王", "金毛狮王", "紫衫龙王");
        List<User> map = map(strings, User::new);
        System.out.println(map);
        /**
         * 如果你的构造函数的签名是User(Integer age,String name),可以使用BiFunction
         */
        BiFunction<Integer,String,User> c5 = User::new;
        User user_c5 = c5.apply(1, "小宝宝");
        System.out.println(user_c5);
        // 同样这等价于
        BiFunction<Integer,String,User> c6 = (Integer age,String name)-> new User(age,name);
        User user_c6 = c6.apply(2, "小宝宝2");
        System.out.println(user_c6);


    }
}
