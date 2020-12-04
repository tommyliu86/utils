package com.lwf.mysqlvolume.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lwf.mysqlvolume.demo.dao.StudentMapper;
import com.lwf.mysqlvolume.demo.domain.StudentPo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-18 14:57
 */
@SpringBootApplication
@Slf4j
@MapperScan
public class SpringApplication implements CommandLineRunner {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    StudentMapper studentMapper;

    public void run(String... args) throws Exception {
        List<StudentPo> studentPos = studentMapper.listAll();
        log.info("students is :{}",objectMapper.writeValueAsString(studentPos));
        StudentPo studentPo = new StudentPo();
        studentPo.setName("forId");
        studentPo.setAge(1);
        studentPo.setIdentity("nv");
        int insert = studentMapper.Insert(studentPo);
        log.info("insert student is :{}",objectMapper.writeValueAsString(studentPo));
    }
}
