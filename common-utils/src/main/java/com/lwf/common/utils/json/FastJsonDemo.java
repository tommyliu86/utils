package com.lwf.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-18 17:39
 */
public class FastJsonDemo {
    public static void main(String[] args) throws JsonProcessingException {
        testFieldType(args);
    }

    public static void testFieldType(String[] args) throws JsonProcessingException {
        String json="{\n" +
                "            \"skuId\": 271235,\n" +
                "            \"mainSkuId\": 271235,\n" +
                "            \"score1\": 0,\n" +
                "            \"score2\": 0,\n" +
                "            \"score3\": 1,\n" +
                "            \"score4\": 20,\n" +
                "            \"score5\": 43,\n" +
                "            \"averageScore\": 5,\n" +
                "            \"defaultGoodCount\": 0,\n" +
                "            \"defaultGoodCountStr\": \"0\",\n" +
                "            \"commentCount\": 64,\n" +
                "            \"commentCountStr\": \"60+\",\n" +
                "            \"goodCount\": 63,\n" +
                "            \"goodCountStr\": \"60+\",\n" +
                "            \"goodRate\": 0.98,\n" +
                "            \"goodRateShow\": 98,\n" +
                "            \"generalCount\": 1,\n" +
                "            \"generalCountStr\": \"1\",\n" +
                "            \"generalRate\": 0.02,\n" +
                "            \"generalRateShow\": 2,\n" +
                "            \"poorCount\": 0,\n" +
                "            \"poorCountStr\": \"0\",\n" +
                "            \"poorRate\": 0.0,\n" +
                "            \"poorRateShow\": 0,\n" +
                "            \"videoCount\": 0,\n" +
                "            \"videoCountStr\": \"0\",\n" +
                "            \"afterCount\": 0,\n" +
                "            \"afterCountStr\": \"0\",\n" +
                "            \"showCount\": 8,\n" +
                "            \"showCountStr\": \"8\",\n" +
                "            \"oneYear\": 0,\n" +
                "            \"sensitiveBook\": 0,\n" +
                "            \"fixCount\": 0,\n" +
                "            \"plusCount\": 0,\n" +
                "            \"plusCountStr\": \"0\",\n" +
                "            \"buyerShow\": 0,\n" +
                "            \"poorRateStyle\": 0,\n" +
                "            \"generalRateStyle\": 3,\n" +
                "            \"goodRateStyle\": 147\n" +
                "        }";
        newSkuCommentInfo newSkuCommentInfo = JSON.parseObject(json, newSkuCommentInfo.class);
        System.out.println(newSkuCommentInfo );
        System.out.println("------------------");
        ObjectMapper objectMapper = new ObjectMapper();
        com.lwf.common.utils.json.newSkuCommentInfo newSkuCommentInfo1 = objectMapper.readValue(json, newSkuCommentInfo.class);
        System.out.println(newSkuCommentInfo1);
    }
    public static void main1(String[] args) {
        String json="{\n" +
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
        //1. 直接转为map，因为jsonobject本身继承了Map接口
        Map<String,Object> map = JSON.parseObject(json, Map.class);
        System.out.println(map);

        //2. 转为JSONObject， 再获取内部的map
        JSONObject jsonObject = JSON.parseObject(json);
        Map<String, Object> innerMap = jsonObject.getInnerMap();
        System.out.println(innerMap);

        //3. 直接使用JSONObject 进行get值,但这里要注意，只能一层一层的解析下去
        JSONObject site = (JSONObject) jsonObject.get("sites");
        System.out.println(site);
        JSONArray site1 = site.getJSONArray("site");
        JSONObject o = (JSONObject) site1.get(0);
        System.out.println(o);
        String url = o.getString("url");
        System.out.println(url);

        System.out.println(new JSONObject().toJSONString());
    }

    public static void main2(String[] args) {
        String json="{\"count\":20000, \"skuList\":[3333333L,444444444L],\"skuInfoList\":[{\"skuId\":\"1231\",\"name\":\"兔子\",\"picUrl\":\"jsf/.....\",\"praiseRate\":\"0.9\",\"praiseCount\":\"100\",\"shopScore\":\"9.00\",\"title\":\"食草动物\"}]}";
        Map<String,Object> map = JSON.parseObject(json, Map.class);
        Object skuInfoList = map.get("skuInfoList");
        System.out.println(skuInfoList.getClass());
        List<BigAiSkuDto> bigAiSkuDtos = JSON.parseArray(skuInfoList.toString(), BigAiSkuDto.class);
        System.out.println(bigAiSkuDtos);
    }

    public static void main3(String[] args) {
        BigAiQuery bigAiQuery = new BigAiQuery();
        bigAiQuery.setCate(new ArrayList<>(Arrays.asList("1","2")));
        bigAiQuery.setCategoryInfo(new HashMap<>());
        Map o = (Map)JSON.toJSON(bigAiQuery);
        System.out.println(o);
        Object categoryInfo = o.get("categoryInfo");
        System.out.println(categoryInfo.getClass());
    }
    public static void main4(String[] args) {
        BigAiQuery bigAiQuery = new BigAiQuery();
        bigAiQuery.setCate(new ArrayList<>(Arrays.asList("1","2")));
        bigAiQuery.setCategoryInfo(new HashMap<>());

        Map o = (Map)JSON.toJSON(bigAiQuery);
        System.out.println(o);
        Object categoryInfo = o.get("categoryInfo");
        System.out.println(categoryInfo.getClass());


        JsonMapper jsonMapper = new JsonMapper();

    }
}
