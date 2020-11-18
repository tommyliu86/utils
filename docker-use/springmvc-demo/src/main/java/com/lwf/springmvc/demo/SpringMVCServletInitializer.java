package com.lwf.springmvc.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-18 10:48
 */
@Configuration
@EnableWebMvc
@ComponentScans({@ComponentScan(basePackageClasses = SpringMVCServletInitializer.class)})
@RestController
public class SpringMVCServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return of(this.getClass());
    }

    protected String[] getServletMappings() {
        return of("/*");
    }
    private static <T> T[] of(T... values){
        return values;
    }
    //springmvc 在不加@注释时，方法入参只能接收RequestParam。
    @GetMapping("/hello")
    public String hello(String name){
        return  "hello world,"+name;
    }
}
