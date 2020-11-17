package com.lwf.common.utils.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-17 11:39
 */
public class StringUtilsUseCase {
    static ObjectMapper objectMapper=new ObjectMapper();
    public static void main(String[] args) throws JsonProcessingException {
        //直接使用split的时候，分隔符是精确匹配模式，而使用stringutils，则会是类似于regex的贪婪匹配模式
        String s="4__12123";
        String[] s1 = StringUtils.tokenizeToStringArray(s, "_");
        System.out.println(objectMapper.writeValueAsString( s1));
        String[] s4 = s.split("_");
        System.out.println(objectMapper.writeValueAsString( s4));

        String s2="4_132123";
        String[] s3 = StringUtils.tokenizeToStringArray(s2, "_");
        System.out.println(objectMapper.writeValueAsString( s3));

    }
}
