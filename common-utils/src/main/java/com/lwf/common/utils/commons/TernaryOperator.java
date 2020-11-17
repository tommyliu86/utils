package com.lwf.common.utils.commons;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-17 13:42
 */
public class TernaryOperator {
    public static void main(String[] args) {
        //三元运算符的优先级最高，因此在每个子项目（元）中可以不加括号
        Integer max=100;
        Integer num=30;
        Integer i1= max-50>num?max-10:max-20-20;
        System.out.println(i1);
    }
}
