package com.lwf.common.utils.spring;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-01-12 19:50
 */
public class Student {
    private String name;
    private Integer age;
    @Autowired
    private Grade grade;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public Grade getGrade(){
        return this.grade;
    }
}
