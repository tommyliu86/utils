package com.lwf.common.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-18 17:55
 */
public class JacksonDemo {
    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\n" +
                "  \"sites\": {\n" +
                "    \"site\": [\n" +
                "      {\n" +
                "        \"id\": \"1\",\n" +
                "        \"name\": \"菜鸟教程\",\n" +
                "        \"url\": \"www.runoob.com\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"2\",\n" +
                "        \"name\": \"菜鸟工具\",\n" +
                "        \"url\": \"c.runoob.com\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"3\",\n" +
                "        \"name\": \"Google\",\n" +
                "        \"url\": \"www.google.com\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        //常用的解析api时ObjectMapper，在2.10之后提供了JsonMapper，以及MapperBuilder
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        TimeDemo timeDemo = new TimeDemo();
        timeDemo.setTime(LocalDateTime.now());
        String s = objectMapper.writeValueAsString(timeDemo);
        System.out.println(s);


        /**jackson 提供了支持java8的module来进行java8中提供的新的time模式，通过module的注册就可以使用，
         *但这里要注意，objectmapper必须在使用之前就进行注册module ，不然无法生效，同时需要把 SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
         *这个关闭，不然会序列化成一个时间戳格式的数组，
         */
        objectMapper.findAndRegisterModules();
        try {
            System.out.println("try:"+ objectMapper.writeValueAsString(timeDemo));
            System.out.println("使用过的objectmapper注册module无法生效！");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ObjectMapper andRegisterModules = new ObjectMapper().findAndRegisterModules();
        andRegisterModules.findAndRegisterModules();
        andRegisterModules.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String timeJson = andRegisterModules.writeValueAsString(timeDemo);
        System.out.println(timeJson);
        TimeDemo timeDemo1 = andRegisterModules.readValue(timeJson, timeDemo.getClass());
        System.out.println(timeDemo1);
    }

    public static class TimeDemo {
        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }

        private LocalDateTime time;
    }
}
