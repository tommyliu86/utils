package com.lwf.common.utils.convert;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-16 17:39
 */
public class DateAndCalendar {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        //获取到月底的时间差值
        //date的设定时间操作需要借助于common.lang包中的DateUtils来进行设置

        Date date1 = new Date();
        System.out.println(simpleDateFormat.format(date1));
        Date endDate = DateUtils.addMonths(date1, 1);
        Date date = DateUtils.setDays(endDate, 1);
        System.out.println(simpleDateFormat.format(date));


        //第二种方式，直接构建一个新的date 但是这些方法都已经标注了废弃

        Date date2 = new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate());
        System.out.println(date);
        System.out.println(endDate);
        Long ttl = (date.getTime() - date1.getTime()) / 1000;
        System.out.println(ttl);

        //使用calendar,可以进行date的操作
        Calendar calendar = Calendar.getInstance();
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));


    }
}
