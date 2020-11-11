package com.lwf.common.utils.juc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-10 19:18
 */
public class ParallelUseCase {
    public static void main(String[] args) {
        List<Integer> list=of(1,2,3,4);
        list.parallelStream().forEach(System.out::println);
    }
    private static List<Integer> of(Integer... values) {
        return new ArrayList<Integer>(Arrays.asList(values));
    }
}
