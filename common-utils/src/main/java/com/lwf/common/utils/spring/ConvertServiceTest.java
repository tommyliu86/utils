package com.lwf.common.utils.spring;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2021-02-20 16:40
 */
public class ConvertServiceTest {
    public static void main(String[] args) {
        ConversionService conversionService=new GenericConversionService();
        System.out.println(conversionService.canConvert(Long.class, String.class));
        System.out.println(conversionService.canConvert(Double.class, String.class));
        Double d=new Double("1.0012312312312");
        Object dObj=d;
        System.out.println(d.toString());
        Long l=10270914150l;
        System.out.println(l.toString());
    }
}
