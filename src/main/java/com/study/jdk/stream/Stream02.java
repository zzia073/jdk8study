package com.study.jdk.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：fei
 * @date ：Created in 2019/11/5 0005 10:00
 */
public class Stream02 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        long count = list.stream().count();
        System.out.println(count);
    }
}
