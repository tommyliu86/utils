package com.lwf.common.utils.spring;

import com.alibaba.fastjson.JSON;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-02-19 16:55
 */
public class ReflectTest {
    public static void main(String[] args) {

        Class studentSonClass = StudentSon.class;
        while (studentSonClass!=Object.class){
            System.out.println(studentSonClass.getName());
            studentSonClass = studentSonClass.getSuperclass();
        }
        System.out.println(studentSonClass.getName());

        Field[] declaredFields = Student.class.getDeclaredFields();
        System.out.println(JSON.toJSONString(declaredFields));
        Field[] declaredFields1 = StudentSon.class.getDeclaredFields();
        System.out.println(JSON.toJSONString(declaredFields1));

        StudentSon studentSon = new StudentSon();
        studentSon.setFriend("1");
        studentSon.setAge(3);
        studentSon.setName("2");
        for (Field field : declaredFields1) {
            field.setAccessible(true);
            Object field1 = ReflectionUtils.getField(field, studentSon);
            System.out.println(field1.toString());
        }
        Class<?> superclass = studentSon.getClass().getSuperclass();
        Field[] declaredFields2 = superclass.getDeclaredFields();
        for (Field field : declaredFields2) {
            field.setAccessible(true);
            Object field1 = ReflectionUtils.getField(field, studentSon);
            System.out.println(field1.toString());
        }
    }

}
