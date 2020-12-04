package com.lwf.common.utils.commons;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-24 18:54
 */
public class StreamUseCase {
    public static void main(String[] args) {
        //stream group by 的 foreach写法
        List<Test> stringList = new ArrayList<>();
        Map<Object, List<Test>> collect = new HashMap<>();
        for (Test po : stringList) {
            collect.computeIfAbsent(po.id, k -> new ArrayList<>()).add(po);
        }
        //stream 写法
        Map<Integer, List<Test>> collectMap = stringList.stream().collect(Collectors.groupingBy(test -> test.id));
    }

    public class Test {
        public String name;
        public Integer id;
        public Date time;
        public Long identity;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public Long getIdentity() {
            return identity;
        }

        public void setIdentity(Long identity) {
            this.identity = identity;
        }
    }
    //比较的使用
    public static void forcomparor(String[] args) {
        List<Test> list = new ArrayList<>();
        list.stream().sorted(
                //要compare的数据如果有null，则需要先使用Comparator.nullsFirst/nullslast api提供的包装来进行包装。这样可以提供null值比较方法，且避免null值比较报错
                //链式编程可以进行字段正序、倒序的使用。
                Comparator.comparing(Test::getName, Comparator.nullsFirst(String::compareTo)).reversed()
                        .thenComparing(Test::getId, Comparator.nullsFirst(Integer::compareTo))
                        .thenComparing(Test::getTime, Comparator.nullsFirst(Date::compareTo))
                        //如果单独这个字段是倒序排列，则需要使用Comparator.comparing 包装并单独reversed，这样是单独的这个字段进行倒序
                        .thenComparing(Comparator.comparing( Test::getIdentity, Comparator.nullsFirst(Long::compareTo)).reversed())
        ).collect(Collectors.toList());
        //取出指定条数数据
        final List<Test> collect = list.stream().skip(3)
                .limit((long) list.size()).collect(Collectors.toList());

    }
}
