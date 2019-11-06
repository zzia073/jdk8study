package com.study.jdk.lambda.functionalinterface;

/**
 * @author ：fei
 * @date ：Created in 2019/11/1 0001 11:30
 */
@FunctionalInterface
public interface ObjectMethodNoPlus {
    void say();
    @Override
    String toString();
}
