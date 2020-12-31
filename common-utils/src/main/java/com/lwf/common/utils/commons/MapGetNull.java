package com.lwf.common.utils.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-12-21 10:21
 */
public class MapGetNull {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        Map<String, Integer> stringIntegerMap = (Map<String, Integer>) map.get("123");
        System.out.println(stringIntegerMap==null);
    }
}
