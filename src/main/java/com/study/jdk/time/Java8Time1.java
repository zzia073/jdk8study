package com.study.jdk.time;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ：fei
 * @date ：Created in 2019/11/8 0008 10:05
 */
public class Java8Time1 {
    public static void main(String[] args) {
        //关注年月日
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear() + "," + localDate.getMonthValue() + "," + localDate.getDayOfMonth());
        System.out.println("=====================================");

        LocalDate localDate1 = LocalDate.of(2019,11,9);
        System.out.println(localDate1);
        System.out.println("=====================================");

        //判断月和日是否是同一天，比如生日的判定
        LocalDate localDate2 = LocalDate.of(1988,10,16);
        MonthDay monthDay = MonthDay.of(localDate2.getMonth(),localDate2.getDayOfMonth());
        MonthDay monthDay1 = MonthDay.from(LocalDate.of(2012,10,16));
        System.out.println(monthDay.equals(monthDay1));
        System.out.println("======================================");
        //关注时分秒
        LocalTime time = LocalTime.now();
        System.out.println(time);
        LocalTime time1 = time.plusHours(3).plusMinutes(30);
        System.out.println(time1);
        System.out.println("======================================");
        //两周后
        LocalDate towWeeksLater = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(towWeeksLater);
        System.out.println(towWeeksLater.getDayOfWeek().getValue());
        //获取两年内所有的周五
        LocalDate now = LocalDate.now();
        LocalDate towYearsLater = now.plusYears(2);
        List<LocalDate> fridayInTowYears = new ArrayList<>();
        while (now.isBefore(towYearsLater)) {
            if (now.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                LocalDate firstFriday = LocalDate.from(now);
                fridayInTowYears.add(firstFriday);
            }
            now = now.plusDays(1);
        }
        while (now.isBefore(towYearsLater)){
            LocalDate everyFriday = LocalDate.from(now = now.plusWeeks(1));
            fridayInTowYears.add(everyFriday);
        }
        System.out.println("======================================");
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println("======================================");
        LocalDate localDate3 = LocalDate.of(2019, 11, 9);
        LocalDate localDate4 = LocalDate.now();

        System.out.println(localDate3.isBefore(localDate4));
        System.out.println(localDate3.isAfter(localDate4));
        System.out.println(localDate3.isEqual(localDate4));
        System.out.println("======================================");
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        TreeSet<String> treeSet = new TreeSet<>(availableZoneIds);
        treeSet.forEach(System.out::println);
        System.out.println("======================================");

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);
        System.out.println("======================================");
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());
        YearMonth yearMonth1 = YearMonth.of(2019,8);
        System.out.println(yearMonth1.lengthOfMonth());
        System.out.println(yearMonth1.lengthOfYear());
        System.out.println("======================================");
        LocalDate localDate5 = LocalDate.of(2017, 11, 5);
        LocalDate localDate6 = LocalDate.now();
        Period period = Period.between(localDate5, localDate6);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println("======================================");
        System.out.println(Instant.now());
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());

    }

}
