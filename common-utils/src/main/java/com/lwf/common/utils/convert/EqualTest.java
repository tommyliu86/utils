package com.lwf.common.utils.convert;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-11 10:59
 */
public class EqualTest {
    public static void main(String[] args) {
        Integer i=1000000;

        System.out.println(i == 1000000);
        System.out.println(i.equals(1000000));
        System.out.println(i.equals(new Integer(1000000)));
        System.out.println("--------big integer>128--------");
        Integer i2=1000000;
        System.out.println(i == i2);
        System.out.println(i.equals(i2));
        System.out.println("--------integer<128--------");
        Integer i3=1;
        Integer i4=1;
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));
        /**
         * 1.对于==号来说，如果==号两边都是包装类型，则会比较两个类型是否指向同一个实例，
         * 而如果有一边是基础类型，则会使用equals来代替，也就是比较的是两个的实际值是否相等
         * 2.对于equals方法，首先会比较两个的类型是否一致，也就是class是否是相同的来进行fastfail，如果相同则再进行内部值的比较。重写equals方法需要 注意
         *   public boolean equals(Object obj) {
         *         if (obj instanceof Integer) {
         *             return value == ((Integer)obj).intValue();
         *         }
         *         return false;
         *     }
         *     该方法是Integer的具体代码，可以看到首先进行了 instanceof 来判断类型
         */
    }
}
