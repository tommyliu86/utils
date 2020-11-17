package com.lwf.docker.tomcat.tomcatdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class TomcatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatDemoApplication.class, args);
    }
    //对于war包来说，会自动包含包名作为root的path，因此该/hello路径前需要加上包名才能访问
    @GetMapping("/hello")
    public String helloWorld(String name){
        log.info("input is :"+name);
        return "hello world"+name;
    }

}
