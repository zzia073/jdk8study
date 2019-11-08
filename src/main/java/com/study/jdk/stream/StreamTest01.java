package com.study.jdk.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：fei
 * @date ：Created in 2019/11/4 0004 10:24
 * 流的迭代方式最终会以iterator的的方式去迭代，
 * 所以如果只是一个简单的迭代用流的方式去迭代不会有简单的迭代器迭代快，因为流还会创建很多的对象
 * Stream操作分类
 * ====================================================================================================================================
 * ||                                  || 	                        || unordered() filter() map() mapToInt()                         ||
 * ||                                  || 无状态(Stateless)          || mapToLong() mapToDouble()flatMap() flatMapToInt()             ||
 * || 中间操作(Intermediate operations) ||                           || flatMapToLong() flatMapToDouble() peek()                      ||
 * ||                                  ||==============================================================================================
 * ||                                  || 有状态(Stateful)	        || distinct() sorted() sorted() limit() skip()                   ||
 * ||==================================================================================================================================
 * ||                                  || 非短路操作	                || forEach() forEachOrdered() toArray() reduce()                 ||
 * ||                                  ||                           || collect() max() min() count()                                 ||
 * ||结束操作(Terminal operations)	   ||==============================================================================================
 * ||                                  || 短路操作(short-circuiting)	|| anyMatch() allMatch() noneMatch() findFirst() findAny()       ||
 * ====================================================================================================================================
 * jdk8对每个流操作都表示为一个stage阶段
 * ReferencePipeline 表示流的源或中间操作的抽象
 * ReferencePipeline 中的内部类 Head 表示的是 ReferencePipeline 的 source stage 源阶段
 * 构造流的时候就是从这个 Head 开始的所以 StreamSupport.stream 返回的是 Head 对象
 * ReferencePipeline 和 ReferencePipeline.Head 大部分属性都是相同的， Head 中没有 previousStage
 * 每个pipeline的构造方法都只是为了构造Stream的Head，中间阶段不是通过直接调用构造方法构造出来的，
 * 是通过各种流的Op对象间接调用构造方法实现StatelessOp和StatefulOp
 *
 * TerminalOp 终止操作 FindOp ForeachOp MatchOp ReduceOp
 *
 * Sink extends Consumer (Sink之所以是Consumer是因为它的目的只需要包装流执行自己的操作并将结果传给下游流的Sink，相当于对流的消费)
 * Sink 有两个状态 initial 和 active
 * Sink 的四个方法 begin accept end cancellationRequested
 *  begin 方法是在 真正执行流操作之前初始化Sink 比如sort 流的Sink begin方法会初始化一个容器存放源中的元素 以便在end方法中排序
 *  accept 方法包装了流的操作真正执行操作比如sort方法的accept只是把流中的元素放入begin初始化的容器中这是
 *  end 方法是把流中的元素执行完操作后传入下一个流操作中进行操作
 *  cancellationRequested 方法是用来实现流的熔断操作的，如果返回true则不再进行之后的流操作
 *  以上的步骤是有状态的流操作的步骤，所有有状态的流都实现了begin和end方法
 *  无状态的流操作一般都是在accept方法中调用自己的操作之后传入下一个流中进行操作
 * Sink 是把流的stage中的操作包装到 accept 方法中
 * begin 方法把Sink变成initial状态 end方法把Sink变成active状态
 * 调用步骤为 begin -> accept -> end -> begin -> accept -> end ...
 * 参考 博客 https://www.cnblogs.com/CarpenterLee/archive/2017/03/28/6637118.html
 *
 */
public class StreamTest01 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","fly");
        list.stream().forEach(System.out::println);
        list.stream().map(i -> i).forEach(System.out::println);
    }

}
