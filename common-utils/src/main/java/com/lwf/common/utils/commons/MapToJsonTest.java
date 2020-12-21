package com.lwf.common.utils.commons;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-12-09 14:01
 */
public class MapToJsonTest {
    public static void main(String[] args) {
        Map<student,EEnum> map=new HashMap<>();
        student student = new student();
        student.setAid("zs");
        map.putIfAbsent(student,EEnum.EQ);
        System.out.println(JSON.toJSONString(map));
    }
    public static class student{
        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        String aid;
    }
    public enum  EEnum{
        EQ,NEQ;

    }
}
