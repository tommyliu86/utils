package com.lwf.mysqlvolume.demo.dao;

import com.lwf.mysqlvolume.demo.domain.StudentPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-18 15:05
 */
@Mapper
public interface StudentMapper {
    @Select("select * from students")
     List<StudentPo> listAll();
    @Insert("insert into students(name,age,`identity`) VALUES(#{name},#{age},#{identity})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int Insert(StudentPo studentPo);
}
