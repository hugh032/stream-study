package com.hugh.stream.model;

import java.math.BigDecimal;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private BigDecimal money;
    private Integer sex;

    public User(Integer id, String name, Integer age, BigDecimal money, Integer sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.money = money;
        this.sex = sex;
    }
    public User(){

    }

    public User(Integer age,String name ) {
        this.name = name;
        this.age = age;
    }

    public User(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", sex=" + sex +
                '}';
    }
}
