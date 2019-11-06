package com.study.jdk.stream;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @author ：fei
 * @date ：Created in 2019/11/4 0004 17:05
 */
public class ConsumerInstanceof {

    public static void main(String[] args) {
        Consumer<Integer> consumer = (i) -> System.out.println(i);
        IntConsumer intConsumer = (i) -> System.out.println(i);
        //instanceof语法 实例对象 instanceof 类型
        //true
        System.out.println(consumer instanceof Consumer);
        //true
        System.out.println(intConsumer instanceof IntConsumer);
        //false
        System.out.println(intConsumer instanceof Consumer);
        //false
        System.out.println(consumer instanceof IntConsumer);

        //面向对象的方式
        forceTransfer(consumer);

        //面向对象的方式进行强制类型转换 该方法不可行
//        forceTransfer((Consumer<Integer>) intConsumer);

        //方法引用的方式
        forceTransfer(consumer);
        forceTransfer((IntConsumer) consumer::accept);

        forceTransfer(intConsumer);
        forceTransfer((Consumer<Integer>) intConsumer::accept);

        transfer(consumer::accept);
        System.out.println("================");
        transfer(intConsumer::accept);
        System.out.println("===============");
        Consumer c = new IntConsumerAdapter();
        transfer(c);
        System.out.println(c instanceof Consumer);
        System.out.println(c instanceof IntConsumer);
    }
    public static void forceTransfer(Consumer<Integer> consumer){
        consumer.accept(1);
    }
    public static void forceTransfer(IntConsumer consumer){
        consumer.accept(2);
    }
    public static void transfer(Consumer<? super Integer> consumer){
        if (consumer instanceof IntConsumer){
            System.out.println("1111111111");
            forceTransfer((IntConsumer) consumer);
        } else {
            consumer.accept(33);
        }
    }

}
class IntConsumerAdapter implements Consumer<Integer>, IntConsumer{
    @Override
    public void accept(Integer integer) {
        System.out.println("consumer");
    }

    @Override
    public void accept(int value) {
        System.out.println("intConsumer");
    }
}