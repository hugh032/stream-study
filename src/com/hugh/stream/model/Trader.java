package com.hugh.stream.model;

public class Trader{
    /**
     * 交易员名字
     */
    private final String name;
    /**
     * 工作地点
     */
    private final String city;

    private final Integer sex;
    public Trader(String n, String c,Integer s){
        this.name = n;
        this.city = c;
        this.sex = s;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }

    public Integer getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", sex=" + sex +
                '}';
    }
}