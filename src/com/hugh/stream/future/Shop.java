package com.hugh.stream.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 这是一个商品最低价格查询器，
 * 模拟从不同电商平台查询商品的价格
 */
public class Shop {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        double bestPrice = getBestPrice();
        System.out.println("全网最低价==" + bestPrice);
        System.out.println(System.currentTimeMillis() - start + "[cost(ms)]");
        System.out.println("**********************************************");


    }

    /**
     * 查询价格
     *
     * @param product
     * @param shopSource
     * @return
     */
    public static double queryPrice(String product, String shopSource) {
        delay();
        double price = new Random().nextDouble() * product.charAt(0) + product.charAt(1);
        System.out.println(shopSource + " 查询商品 价格= " + price);
        return price;
    }


    /**
     * 延迟方法，模拟查询各个电商平台价格接口的耗时
     */
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回最低价
     *
     * @return
     */
    public static double getBestPrice() {
        List<Double> prices = new ArrayList<>();
        double taobaoPrice = queryPrice("iphone12", "淘宝");
        double jdPrice = queryPrice("iphone12", "京东");
        double pddPrice = queryPrice("iphone12", "拼多多");
        prices.add(taobaoPrice);
        prices.add(jdPrice);
        prices.add(pddPrice);
        Double bestPrice = prices.stream().min(Double::compareTo).get();
        return bestPrice;
    }


}
