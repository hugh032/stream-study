package com.hugh.stream.model;

public class Transaction{
    /**
     * 交易员
     */
    private final Trader trader;
    /**
     * 交易时间
     */
    private final int year;
    /**
     * 交易额
     */
    private final int value;


    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
    public Trader getTrader(){
        return this.trader;
    }
    public int getYear(){
        return this.year;
    }
    public int getValue(){
        return this.value;
    }
    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}