package com.study.jdk.test;

import com.study.jdk.collector.MyCollectorToList;
import com.study.jdk.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：fei
 * @date ：Created in 2019/10/30 0030 17:08
 */
public class CollectorTest {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student(1, "张三", 12);
        Student s2 = new Student(2, "李四", 23);
        Student s3 = new Student(3, "王五", 31);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        List<Integer> collect = students.stream().collect(new MyCollectorToList<>());
        collect.forEach(System.out::println);
    }
}
