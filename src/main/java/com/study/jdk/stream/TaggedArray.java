package com.study.jdk.stream;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author ：fei
 * @date ：Created in 2019/11/4 0004 14:11
 * Spliterator 中的一个示例
 * SUBSIZE 特性值表示
 * 分割前的estimateSize() = 分割后的estimateSize() + 分割返回的estimateSize();
 */
public class TaggedArray<T> {
    //构造之后不可变
    private final Object[] elements;
    TaggedArray(T[] data, Object[] tags){
        int size = data.length;
        if (tags.length != size){
            throw new IllegalArgumentException();
        }
        this.elements = new Object[2 * size];
        //元素中放一个数据一个标签
        for (int i = 0, j = 0; i < size; ++i){
            elements[j++] = data[i];
            elements[j++] = tags[i];
        }
    }
    public Spliterator<T> spliterator() {
        return new TaggedArraySpliterator<>(elements, 0, elements.length);
    }

    static class TaggedArraySpliterator<T> implements Spliterator<T> {
        private final Object[] array;
        private int origin;//当前索引，
        private final int fence; //最大索引下一个位置
        TaggedArraySpliterator(Object[] array, int origin, int fence){
            this.array = array;
            this.origin = origin;
            this.fence = fence;
        }

        /**
         * 循环遍历剩下的所有元素
         * @param action
         */
        @Override
        public void forEachRemaining(Consumer<? super T> action){
            for (; origin < fence; origin +=2){
                action.accept((T) array[origin]);
            }
        }

        /**
         * 试图去尝试获取下一个元素并消费它
         * 如果原始位置没有超过最大位置则获取并消费，否则返回false
         * hasNext和next两个方法的合并
         * @param action 消费动作
         * @return 是否有下一个元素
         */
        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (origin < fence){
                action.accept((T) array[origin]);
                origin += 2;
                return true;
            }
            return false;
        }

        /**
         * 该方法试图去分割原Spliterator为左右两部分，
         * 如果分割成功返回一个新的左侧的部分，原来的origin移动到mid位置，
         * 原来的留下的是右侧的部分
         * 分割失败返回null
         * @return 新的Spliterator
         */
        @Override
        public Spliterator<T> trySplit() {
            int lo = origin;
            //偶数最后一位是0奇数最后一位是1，相当于除以2如果是偶数不变奇数减一，结果为偶数
            int mid = ((lo +fence) >>> 1) & ~1;
            //切分出左侧的部分
            if (lo < mid) {
                //重置起始位置为中间位置
                origin = mid;
                //新构建一个左侧部分的Spliterator
                return new TaggedArraySpliterator<>(array, lo, mid);
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return (long)((fence - origin) / 2);
        }

        @Override
        public int characteristics() {
            return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
        }
    }
}
