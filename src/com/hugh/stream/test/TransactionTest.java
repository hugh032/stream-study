package com.hugh.stream.test;

import com.hugh.stream.common.TransactionTestDataBuilder;
import com.hugh.stream.enums.TraderLevel;
import com.hugh.stream.model.Trader;
import com.hugh.stream.model.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流操作练习题
 */
public class TransactionTest {
    /**
     * 数据源
     */
    public static final List<Transaction> transactions = TransactionTestDataBuilder.buildData();

    public static void main(String[] args) {
        System.out.println("test1:" + test1());
        System.out.println("test2:" + test2());
        System.out.println("test3:" + test3());
        System.out.println("test4:" + test4());
        System.out.println("test5:" + test5());
        test6();
        System.out.println("test7:" + test7());
        System.out.println("test8:" + test8());
        System.out.println("test9:" + test9());
        System.out.println("test10:" + test10());
        System.out.println("test11:" + test11());
        System.out.println("test12:" + test12());
        System.out.println("test13:" + test13());
        System.out.println("test14:" + test14());
        System.out.println("test15:" + test15());
        System.out.println("test16:" + test16());

    }

    /**
     * test1:
     * 找出2011年的所有交易并按交易额排序（从低到高）
     */
    public static List<Transaction> test1() {
        List<Transaction> result = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * test2:
     * 交易员都在哪些不同的城市工作过
     */
    public static List<String> test2() {
        List<String> result = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        return result;
    }

    /**
     * test3:
     * 查找所有来自于剑桥的交易员，并按姓名排序
     */
    public static List<Trader> test3() {
        List<Trader> result = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(trader -> trader.getName()))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * test4:
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    public static String test4() {
        String result = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2 + ",");// 逐个拼接每个名字，得到一个将所有名字连接起来的String
        return result;
    }

    /**
     * test5:
     * 有没有交易员是在米兰工作的
     */
    public static Boolean test5() {
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        return milanBased;
    }

    /**
     * test6:
     * 打印生活在剑桥的交易员的所有交易额
     */
    public static void test6() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .forEach(System.out::println);
    }

    /**
     * test7:
     * 所有交易中，最高的交易额是多少
     */
    public static int test7() {
        Integer highestValue = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue))
                .map(Transaction::getValue)
                .get();
        //Integer highestValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max).get();
        return highestValue;
    }

    /**
     * test8:
     * 找到交易额最小的交易
     */
    public static Transaction test8() {
        Transaction smallestTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .get();
        // 使用reduce
//        Transaction smallestTransaction = transactions.stream()
//                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2).get();
        return smallestTransaction;
    }

    /**
     * test9:
     * 所有交易员的交易业绩和
     */
    public static int test9() {
        // 通过reduce计算
//        int sum = transactions.stream()
//                .map(Transaction::getValue)
//                .reduce(Integer::sum)
//                .get();
        // 可以通过原始数据类型流来解决装箱的复杂性
//        int sum = transactions.stream()
//                .mapToInt(Transaction::getValue)
//                .sum();
        // 也可以使用collect
        Integer sum = transactions.stream().collect(Collectors.summingInt(Transaction::getValue));
        return sum;
    }

    /**
     * test10:
     * 按交易员分组
     */
    public static Map<Trader, List<Transaction>> test10() {
        Map<Trader, List<Transaction>> resultMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getTrader));
        return resultMap;
    }

    /**
     * test11:
     * 交易员的平均业绩
     */
    public static Double test11() {
        Double collect = transactions.stream().collect(Collectors.averagingDouble(Transaction::getValue));
        return collect;
    }

    /**
     * test12:
     * 拼接交易员名字
     */
    public static String test12() {
        String collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .collect(Collectors.joining(","));
        return collect;
    }

    /**
     * test13:
     * 交易额综合
     * 使用reducing 计算
     */
    public static Integer test13() {
        Integer integer = transactions.stream()
                .map(Transaction::getValue)
                .collect(Collectors.reducing((v1, v2) -> v1 + v2)).get();

        return integer;
    }

    /**
     * test14:
     * 按业绩级别分组 500以下属于初级，500-1000中级，1000以上高级
     */
    @SuppressWarnings("all")
    public static Map<TraderLevel, List<Transaction>> test14() {
        Map<TraderLevel, List<Transaction>> collect = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> {
                            if (transaction.getValue() < 500) {
                                return TraderLevel.LOW;
                            } else if (transaction.getValue() >= 500 && transaction.getValue() < 1000) {
                                return TraderLevel.MID;
                            } else {
                                return TraderLevel.HIGH;
                            }
                        }
                ));
        return collect;
    }

    /**
     * test15:
     * 接上一题
     * 按业绩分组后把每个等级业绩最好的业务员找出来
     */
    @SuppressWarnings("all")
    public static Map<TraderLevel,Optional<Transaction>> test15() {
        Map<TraderLevel, Optional<Transaction>> collect = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> {
                            if (transaction.getValue() < 500) {
                                return TraderLevel.LOW;
                            } else if (transaction.getValue() >= 500 && transaction.getValue() < 1000) {
                                return TraderLevel.MID;
                            } else {
                                return TraderLevel.HIGH;
                            }
                        }, Collectors.maxBy(Comparator.comparingInt(Transaction::getValue))
                ));
        return collect;
    }

    /**
     * test16:
     * 接上一题：
     * 上面的结果返回的是Map<TraderLevel,Optional<Transaction>> ,map中的值对于我们来说没什么用，
     * 需要把收集器返回的结果转换为另一种类型你可以使用Collectors.collectingAndThen工厂方法返回的收集器
     */
    @SuppressWarnings("all")
    public static Map<TraderLevel,Transaction> test16() {
        Map<TraderLevel, Transaction> collect = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> {
                            if (transaction.getValue() < 500) {
                                return TraderLevel.LOW;
                            } else if (transaction.getValue() >= 500 && transaction.getValue() < 1000) {
                                return TraderLevel.MID;
                            } else {
                                return TraderLevel.HIGH;
                            }
                        }, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Transaction::getValue)),Optional::get)
                ));
        return collect;
    }
}
