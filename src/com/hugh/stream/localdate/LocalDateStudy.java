package com.hugh.stream.localdate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class LocalDateStudy {
    public static void main(String[] args) {
        /* LocalDate*/
        LocalDate date = LocalDate.of(2020, 12, 24);
        System.out.println(date);
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.lengthOfMonth());
        System.out.println(date.isLeapYear());
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));
        /* LocalTime*/
        LocalTime time = LocalTime.of(13, 48, 33);
        System.out.println(time);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        System.out.println(time.getNano());
        LocalDate parse = LocalDate.parse("2016-07-15");
        LocalTime parse1 = LocalTime.parse("15:59:59");
        System.out.println(parse);
        System.out.println(parse1);
        /* LocalDateTime*/
        LocalDateTime dateTime = date.atTime(time);
        LocalDateTime dateTime1 = time.atDate(date);
        LocalDateTime.of(date, time);
        System.out.println(LocalDateTime.of(2020, Month.OCTOBER, 24, 16, 25, 07));
        System.out.println(dateTime);
        System.out.println(dateTime1);
        /* 对时间的修改*/
        LocalDateTime origin = LocalDateTime.of(date, time);
        LocalDateTime new1 = origin.withYear(1987).withMonth(Month.APRIL.getValue()).withDayOfMonth(1);
        LocalDateTime new2 = origin.with(ChronoField.YEAR, 1990);
        System.out.println(new1);
        System.out.println(new2);
        /* 以相对的方式修改*/
        LocalDateTime new3 = origin.plusDays(1);
        System.out.println(new3);
        LocalDateTime new4 = origin.plusMonths(1);
        System.out.println(new4);
        LocalDateTime new5 = origin.minusDays(1);
        System.out.println(new5);
        LocalDateTime new6 = origin.plus(1, ChronoUnit.MINUTES);
        System.out.println(new6);
        LocalDateTime new7 = origin.minus(20, ChronoUnit.SECONDS);
        System.out.println(new7);
        /**
         * 2020-12-24 01:00:00的下个周日
         */
        LocalDate of = LocalDate.of(2020, 12, 24);
        // 无论如何都返回下个周四，不管今天是不是周四
        LocalDate with1 = of.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        // 如果调用方法的时候就是星期四，那就返回当日
        LocalDate with2 = of.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
        System.out.println(with1);
        System.out.println(with2);
        /**
         * 一个月中最后一天
         */
        LocalDate with = of.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(with);
        /**
         *  一个月中第1个周日的日期
         */
        LocalDate with3 = of.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.SUNDAY));
        System.out.println(with3);

        /**
         * LocalDate --> String
         */
        String format = of.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String format1 = of.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("format==" + format);
        System.out.println("format1==" + format1);
        /**
         * String --> LocalDate
         */

        LocalDate parse2 = LocalDate.parse("2020-12-25",DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("parse2==" + parse2);
        LocalDate parse3 = LocalDate.parse("20210101", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(parse3);
        /**
         * 创建DateTimeFormatter
         */
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String format2 = localDate.format(dateTimeFormatter);
        System.out.println(format2);
        
    }
}
