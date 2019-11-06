package com.study.jdk.entity;

import java.util.Optional;

/**
 * @author ：fei
 * @date ：Created in 2019/10/30 0030 17:47
 */
public class StringUtil {
    public static String firstCharToUpper(String source){
        String s = Optional.ofNullable(source).orElseGet(String::new);
        return String.valueOf(s.charAt(0)).toUpperCase().concat(s.substring(1));
    }

}
