package com.lwf.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-18 17:39
 */
public class FastJsonDemo {
    public static void main(String[] args) {
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
}
