package com.lwf.common.utils.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-12-11 10:56
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>(Arrays.asList(1,2,3));
        //subList中的toIndex，必须是在数组中的，最大为size
        List<Integer> list1 = list.subList(0, list.size());
        System.out.println(list.equals(list1));
        System.out.println(list == list1);
    }
}
