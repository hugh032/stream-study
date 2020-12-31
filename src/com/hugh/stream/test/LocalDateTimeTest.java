package com.hugh.stream.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

public class LocalDateTimeTest {
    public static void main(String[] args) {
        System.out.println(getLastDayOfWeek(LocalDate.now()));
        System.out.println(getDayOfWeekInMonth(LocalDate.now(), 1, DayOfWeek.SUNDAY));
        System.out.println(getFirstInMonth(LocalDate.now(), DayOfWeek.MONDAY));
        System.out.println(getLastInMonth(LocalDate.now(), DayOfWeek.SUNDAY));
        System.out.println(next(LocalDate.now(), DayOfWeek.FRIDAY));
        System.out.println(nextOrSame(LocalDate.now(), DayOfWeek.FRIDAY));
        System.out.println(nextWorkingDay(LocalDate.now()));
    }

    /**
     * 获得月中最后一天
     *
     * @param date
     * @return
     */
    public static LocalDate getLastDayOfWeek(LocalDate date) {
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return lastDayOfMonth;
    }

    /**
     * 获得一个月中第no个周dayOfWeek的日期
     *
     * @param date
     * @param no
     * @param dayOfWeek
     * @return
     */
    public static LocalDate getDayOfWeekInMonth(LocalDate date, int no, DayOfWeek dayOfWeek) {
        LocalDate result = date.with(TemporalAdjusters.dayOfWeekInMonth(no, dayOfWeek));
        return result;
    }

    /**
     * 同一个月中第一个符合星期几要求的值
     *
     * @param date
     * @param dayOfWeek
     * @return
     */
    public static LocalDate getFirstInMonth(LocalDate date, DayOfWeek dayOfWeek) {
        LocalDate result = date.with(TemporalAdjusters.firstInMonth(dayOfWeek));
        return result;
    }

    /**
     * 同一个月中最后一个符合星期几要求的值
     *
     * @param date
     * @param dayOfWeek
     * @return
     */
    public static LocalDate getLastInMonth(LocalDate date, DayOfWeek dayOfWeek) {
        LocalDate result = date.with(TemporalAdjusters.lastInMonth(dayOfWeek));
        return result;
    }

    /**
     * 获得指定时间后的第一个星期几
     *
     * @param date
     * @param dayOfWeek
     * @return
     */
    public static LocalDate next(LocalDate date, DayOfWeek dayOfWeek) {
        LocalDate result = date.with(TemporalAdjusters.next(dayOfWeek));
        return result;
    }

    /**
     * 获得指定时间后的第一个星期几 ,如果指定时间就是星期几，则返回
     *
     * @param date
     * @param dayOfWeek
     * @return
     */
    public static LocalDate nextOrSame(LocalDate date, DayOfWeek dayOfWeek) {
        LocalDate result = date.with(TemporalAdjusters.nextOrSame(dayOfWeek));
        return result;
    }

    /**
     * 获得下一个工作日
     *
     * @param date
     * @return
     */
    public static LocalDate nextWorkingDay(LocalDate date) {
        TemporalAdjuster temporalAdjuster = temporal -> {
            // 读取当前的日期
            DayOfWeek day = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            // 正常情况+1
            int dayToAdd = 1;
            if (day == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (day == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        };
        return date.with(temporalAdjuster);
    }

}
