package com.hugh.stream.collection;

import com.hugh.stream.common.TestDataBuilder;
import com.hugh.stream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流式集合处理demo
 */
public class CollectionDemo {
    public static void main(String[] args) {
        List<User> users = TestDataBuilder.buildList();
        /**
         * 过滤出年纪大于18岁的 ，按收入从高到低排序，并且提取出名字 将名字放入list
         */
        List<String> collect = users.stream()
                .filter(user -> user.getAge() != null && user.getAge() >= 18)
                .sorted(Comparator.comparing(User::getMoney).reversed())
                .map(User::getName)
                .collect(Collectors.toList());
        System.out.println(collect);
        /**
         * 统计年纪大于20岁的收入和
         */
        BigDecimal sum = users.stream()
                .filter(user -> user.getAge() >= 18)
                .map(User::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // reduce方法第一个参数是初始值，有初始值返回BigDecimal，如果没有初始值而流中又没有数据，就无法求和运算，所以会返回Optional<T>
        System.out.println("sum===" + sum);
        /**
         * 将System.out::println传递给forEach
         */
        users.stream().forEach(System.out::println);
        /**
         * limit 截断流  取返回前N个数据
         */
        List<User> subList = users.stream()
                .filter(user -> user.getSex().equals(1))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("limit ===" + subList);
        /**
         * skip 跳过元素  跳过前N个数据后取值
         */
        List<User> skipList = users.stream()
                .filter(user -> user.getSex().equals(1))
                .skip(2)
                .collect(Collectors.toList());
        System.out.println("skip ===" + skipList);
        /**
         * 获得list中某个字符元素的字段的长度
         *
         * 流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映
         * 射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一
         * 个新版本”而不是去“修改”）。例如，下面的代码把方法引用User::getName传给了map方法，
         * 来提取流中用户的名字
         */
        List<Integer> length = users.stream().map(User::getName).map(String::length).collect(Collectors.toList());
        System.out.println("string length ===" + length);
        /**
         * anyMatch
         * 检查谓词至少匹配一个元素
         * 用户中至少有一个收入大于100的
         */
        boolean anyMatch = users.stream().anyMatch(user -> user.getMoney().compareTo(new BigDecimal(100)) == 1);
        System.out.println("anyMatch ===" + anyMatch);
        /**
         * allMatch
         * 全匹配
         * 用户中是不是所有人都是男的
         */
        boolean isAllBoy = users.stream().allMatch(user -> user.getSex().equals(1));
        System.out.println("isAllBoy === " + isAllBoy);
        /**
         * noneMatch 与 allMatch对应
         * 用户中是不是没有女的
         */
        boolean isNoGirl = users.stream().noneMatch(user -> user.getSex().equals(0));
        System.out.println("isNoGirl ===" + isNoGirl);

        /**
         * 在集合中查找符合条件的数据，有一个就返回，并且打印它
         */
        users.stream()
                .filter(user -> user.getSex().equals(1))
                .findAny()
                .ifPresent(user -> System.out.println(user.getName()));
        /**
         * 查找第一个元素
         *
         * 给定一个数字列表，下面的代码能找出第一个平方
         * 能被3整除的数：
         *
         */

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream()
                .map(i -> i * i)
                .filter(i -> i % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);
        /**
         * 元素求和
         *
         */
        List<Integer> integers1 = Arrays.asList(1, 2, 3, 4, 5);
        // reduce方法接受的参数(T identity, BinaryOperator<T> accumulator);BinaryOperator继承自BiFunction<T, U, R>
        Integer sum1 = integers1.stream().reduce(0, (a, b) -> a + b);
        //  因为Integer有一个静态方法sum() ,所以也可以这样写：
        Integer sum2 = integers1.stream().reduce(0, Integer::sum);
        System.out.println("求和sum1 ===" + sum1);
        System.out.println("求和sum2 ===" + sum2);
        /**
         *
         * 元素相乘
         */
        List<Integer> integers3 = Arrays.asList(1, 2, 3, 4, 5);
        Integer multi = integers3.stream().reduce(1, (a, b) -> a * b);
        System.out.println("求乘积multi ===" + multi);
        /**
         * 找年纪最大的用户，找年纪最小的用户
         */
        User userMax = users.stream().max(Comparator.comparing(User::getAge)).get();
        System.out.println("年纪最大的用户 ===" + userMax.getName());
        User userMin = users.stream().min(Comparator.comparing(User::getAge)).get();
        System.out.println("年纪最小的用户 ===" + userMin.getName());
        // 你可以用reduce
        int maxAgeValue = users.stream().mapToInt(User::getAge).reduce(Integer::max).getAsInt();
        System.out.println("最大的年纪数 ===" + maxAgeValue);
        int minAgeValue = users.stream().mapToInt(User::getAge).reduce(Integer::min).getAsInt();
        System.out.println("最小的年纪数 ===" + minAgeValue);

        /**
         *
         * 计算有多少个用户（用map和reduce ，不能用list.size() 或者 stream().count()）
         *
         */
        // 用map把流中的用户元素映射成1，然后去sum
        Integer userCount = users.stream().map(user -> 1).reduce(Integer::sum).get();
        System.out.println("userCount ===" + userCount);

    }
}
