package com.lwf.common.utils.spring;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-12-10 17:37
 */

public class BeanPostPtest implements BeanPostProcessor {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        Student student =(Student) classPathXmlApplicationContext.getBean("student");
        System.out.println(student.getGrade());
    }
}
