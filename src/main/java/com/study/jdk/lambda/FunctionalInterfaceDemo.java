package com.study.jdk.lambda;

import com.study.jdk.entity.Student;

import java.util.function.Function;

/**
 * @author ：fei
 * @date ：Created in 2019/11/1 0001 11:21
 * 函数式接口
 * 1. 如果一个接口只有一个抽象方法，那么该接口就是一个函数式接口
 * 2. 如果我们在某个接口上声明了 FunctionalInterface 注解，那么编译期就会按照函数是接口的定义来要求该接口
 * 3. 如果某个接口只有一个抽象方法，但我们并没有给该接口声明 FunctionalInterface 注解，那么编译器依旧会将该接口看作是函数式接口
 *  <code>@FunctionalInterface</code>
 *  接口中定义object类的方法不会给函数式接口的抽象方法数加一
 *  方法引用创建一个函数式接口的实例 ArrayList::new | System.out::println 构造方法引用或静态方法引用等
 *  lambda表达式在java中表示的是一个对象，类似原来的匿名内部类
 *
 *  java8新增几个函数式接口
 *
 *  函数式接口的根本是 利用lambda表达式 或者 方法引用的方式 产生一个接口对应的匿名内部类对象，去执行对应函数式接口中唯一的方法
 *  比如 Predicate objectIsNullFunction = Objects::isNull;
 *  class CodeMachine {
 *      boolean checkFunction(Predicate predicate){
 *          return predicate.test();
 *      }
 *      public static void main()
 *  }
 *
 *  Function R apply(T t); 一个输入一个输出 例如 String::toUpperCase
 *      compose(Function before) 组合方法 先应用before.apply再应用调用者
 *      andThen(Function after) 然后方法 先应用apply,在对结果应用after.apply
 *  BiFunction R apply(T t, U u); 两个输入一个输出
 *      BinaryOperator<T> extends BiFunction<T,T,T> 二元操作 两个输入和一个输出是同类型
 *
 *  Predicate boolean test(T t); 一个输入返回boolean 判断 Objects::isNull
 *  BiPredicate boolean test(T t, U u);
 *
 *  Supplier T get(); 生产者 没有输入返回一个结果 例如：Object::new
 *
 *  Consumer void accept(T t); 消费者 一个输入不返回结果 例如：System.out::println
 *  BiConsumer void accept(T t, U u);
 */
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        print(testFunction(new Student(1,"张三",12)));
    }
    static String testFunction(Object o){
        Function<Object,String> function = Object::toString;
        return function.apply(o);
    }
    static void print(Object o){
        System.out.println(o);
    }
}