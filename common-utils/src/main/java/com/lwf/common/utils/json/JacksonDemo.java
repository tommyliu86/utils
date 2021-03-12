package com.lwf.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-18 17:55
 */
public class JacksonDemo {


    public static void main(String[] args) throws JsonProcessingException {
//        testArray();
        testJsonProperty(args);
    }
    static ObjectMapper OBJECT_MAPPER=new ObjectMapper();
    /**
     * 如果对象为Null,返回"null". 如果集合为空集合,返回"[]".
     */
    private static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static <T> T toObject(String jsonValue ,Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonValue, clazz);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static void testJsonProperty(String[] args) throws JsonProcessingException {
        String json="{\n" +
                "    \"product_id\": \"1004705542\",\n" +
                "    \"shop_id\": \"30609\",\n" +
                "    \"on_shelves_time\": \"1466498616000\",\n" +
                "    \"product_name\": \"CHUKCHI楚克奇大型犬小型犬食盆碗宠物不锈钢碗猫碗狗狗碗宠物狗盆猫盆\",\n" +
                "    \"vender_name\": \"南通携创户外休闲用品有限公司\",\n" +
                "    \"brand_id\": \"5511\",\n" +
                "    \"sku_status\": \"1\",\n" +
                "    \"category_id1\": \"6994\",\n" +
                "    \"category_id\": \"7018\",\n" +
                "    \"category_id2\": \"6998\",\n" +
                "    \"off_shelves_time\": \"1387209631000\",\n" +
                "    \"shop_name\": \"携创宠物用品专营店\",\n" +
                "    \"main_sku_id\": \"1019362991\",\n" +
                "    \"img_dfs_url\": \"jfs/t1/163732/8/1207/136261/5ff52550Ed1cb5e40/1b72a0991ebd4a42.jpg\",\n" +
                "    \"sku_name\": \"CHUKCHI楚克奇大型犬小型犬食盆碗宠物不锈钢碗猫碗狗狗碗宠物狗盆猫盆 不锈钢 M-中型底座22CM\",\n" +
                "    \"shop_category\": \"1344679 1344683\",\n" +
                "    \"category_ext_id\": \"0\",\n" +
                "    \"yn\": \"1\",\n" +
                "    \"vender_id\": \"31804\"\n" +
                "}";
        Map<String,Object> parse = (Map<String,Object>)JSON.parse(json);

        String s = toJson(parse);
        System.out.println(s);

        ObjectMapper objectMapper = new ObjectMapper();
        SkuDto skuDto = objectMapper.readValue(json, SkuDto.class);
        SkuDto ss = objectMapper.readValue(s, new TypeReference<SkuDto>() {
        });
        SkuDto o =JacksonDemo.toObject(s,skuDto.getClass());

        System.out.println(skuDto);
    }

    public static void testArray() throws JsonProcessingException {
        String json="[\n" +
                "  1,\n" +
                "  2,\n" +
                "  3\n" +
                "]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> list = objectMapper.readValue(json, new TypeReference<List<Integer>>() {
        });
        System.out.println(list);
    }
    public static void main1(String[] args) throws JsonProcessingException {
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
