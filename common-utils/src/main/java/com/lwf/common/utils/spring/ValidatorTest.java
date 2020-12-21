package com.lwf.common.utils.spring;

import org.hibernate.validator.constraints.Length;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-12-14 10:16
 */
public class ValidatorTest {

    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        student student = new student();
        student.setAge(120);
        student.setName("zslis");
        Set<ConstraintViolation<ValidatorTest.student>> validate = validator.validate(student);
        System.out.println(validate);

    }
    public static  class  student{
        @Length(max = 5)
        private String name;
        @Max(value = 100)
        @Min(value = 0)
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
