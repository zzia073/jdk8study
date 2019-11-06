package com.study.jdk.collector;
import com.study.jdk.entity.EntityWithId;
import com.study.jdk.entity.StringUtil;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author ：fei
 * @date ：Created in 2019/10/30 0030 16:23
 *
 */
public class MyCollectorToList<T> implements Collector<T,List<Integer>,List<Integer>> {

    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Integer>, T> accumulator() {
        return (container, t) -> {
            EntityWithId annotation = t.getClass().getAnnotation(EntityWithId.class);
            if (annotation != null){
                String id = annotation.value();
                try {
                    Object invoke = t.getClass().getMethod("get".concat(StringUtil.firstCharToUpper(id)), null).invoke(t);
                    container.add((Integer) invoke);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (l,r) -> {l.addAll(r); return l;};
    }

    @Override
    public Function<List<Integer>, List<Integer>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }
}
