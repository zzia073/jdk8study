package com.study.jdk.time;

import org.joda.time.DateTime;

/**
 * @author ：fei
 * @date ：Created in 2019/11/7 0007 15:23
 * 格林威治标准时间
 * UTC时间
 * Joda-Time https://www.joda.org
 * Instants 某一时刻的瞬间
 * Intervals 表示两个时刻的间隔
 * Durations 以毫秒为单位的间隔
 * Periods 一段时间，比如一个月，如果你把一个月加到二月的第一天那么得到的是三月的第一天
 * ....
 *
 */
public class TimeZone {
    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));

        System.out.println("==============================");
        DateTime date = today.withDayOfMonth(1);
        System.out.println(date.toString("yyyy-MM-dd"));
        //计算2年前第3个月的最后一天
        DateTime dateTime = new DateTime();
        DateTime towYearAgoThirdMonthLastDay = dateTime.minusYears(2).withMonthOfYear(3).dayOfMonth().withMaximumValue();
        System.out.println(towYearAgoThirdMonthLastDay.toString("yyyy-MM-dd"));

    }
}
