package com.lwf.springbootdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
@Slf4j
public class SpringbootDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }
    @RequestMapping("/hello")
    public String hello(String name){
        log.info("input is :"+name);
        return "hello world "+name;
    }

    /**
     * 测试 springboot中的objectmapper 是否支持了java8中的java.time api
     * 通过查看{@link Jackson2ObjectMapperBuilder} 可以发现如果module在引入路径中，会进行自动注册
     */
    @Autowired
    ObjectMapper objectMapper;

    public void run(String... args) throws Exception {
       log.info("objectmapper class is :{}", objectMapper.getClass());
        TimeDemo timeDemo = new TimeDemo();
        timeDemo.setTime(LocalDateTime.now());
        String timeJson = objectMapper.writeValueAsString(timeDemo);
        log.info("time json is：{}",timeJson);
        TimeDemo timeDemo1 = objectMapper.readValue(timeJson, TimeDemo.class);
        log.info("TimeDemo is "+timeDemo1);
    }
    public static class TimeDemo{
        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }

        private LocalDateTime time;
    }
}
