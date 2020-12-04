package com.lwf.common.utils.spring;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-25 13:58
 */
public class BeanUtilsTest {
    public static void main(String[] args) {
        Student student = new Student().setAge(1).setDate(new Date()).setName("123").setSex("nan");
        Student studentnew = new Student().setDate(new Date()).setSex("nan");

        BeanUtils.copyProperties(studentnew,student);
        System.out.println(student);

    }
    @Data
    @Accessors(chain = true)
    public static class Student{
        private String name;
        private String sex;
        private Integer age;
        private Date date;


    }
}
