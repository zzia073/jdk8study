package com.study.jdk.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author ：fei
 * @date ：Created in 2019/11/8 0008 09:51
 *
 */
public class JodaTest1 {
    //标准UTC时间：2019-11-08T09:22:22.135Z
    public static Date convertUTC2Date(String UTCTime){
        try{
            DateTime dateTime = DateTime.parse(UTCTime, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        }catch (Exception e){
            return null;
        }
    }
    public static String convertDate2UTC(Date javaDate){
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }
    public static String convertDate2LocalDateFormat(Date javaDate, String dateFormat){
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }

    public static void main(String[] args) {
        System.out.println(JodaTest1.convertDate2LocalDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }
}
