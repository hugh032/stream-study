package com.hugh.stream.test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 创建流的方式
 */
public class StreamTest {
    public static void main(String[] args) {
        test1();
        System.out.println("stream test2:" + test2());
        System.out.println("stream test3:" + test3());
        System.out.println("stream test4:" + test4());
        test5();
        test6();
    }

    /**
     * 由值创建流
     */
    public static void test1() {
        Stream<String> stream = Stream.of("java8", "lambda", "in", "action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * 创建一个空流
     */
    public static <T> Stream<T> test2() {
        Stream<T> empty = Stream.empty();
        return empty;
    }

    /**
     * 由数组创建流
     * 计算数组和
     */
    public static int test3() {
        int[] nums = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(nums).sum();
        return sum;
    }

    /**
     * 由文件生成流
     * 计算文件中不同的词语个数
     */
    public static long test4() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("D:\\test.txt"), Charset.defaultCharset())) // java 7 自动关闭资源
        {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))) // 生成单词流
                    .distinct() // 去重
                    .count();   // 统计
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueWords;
    }

    /**
     * 无限流
     * 从0开始取正偶数
     */
    public static void test5() {
        Stream.iterate(0, n -> n + 2)
                .limit(10) // 如果不用limit来限制流 将无穷无尽的计算下去
                .forEach(System.out::println);
    }

    /**
     * stream斐波那契元组数列
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55…  -> (0,1)(1,1)(1,2)(2,3)(3,5)(5,8)(8,13)(13,21)(21,34)(34,55)
     */
    public static void test6() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        System.out.println("**********************************************************************");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
    /**
     *
     */
}
