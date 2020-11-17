package com.lwf.common.utils.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-12 19:50
 */
@Slf4j
public class RestTemplateTest  {
    public static List<JsonNode> getJson(List<String> stringList){

        List<JsonNode> jsonNodeList = stringList.stream().map(RestTemplateTest::createJson).collect(Collectors.toList());
        return jsonNodeList;
    }
    public  static JsonNode createJson(String jsonStr){

        String[] strings = StringUtils.tokenizeToStringArray(jsonStr, ",");
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode()
                .put("contentId", strings[0])
                .put("style", 0)
                .put("status", 3)
                .put("privateStatus", 0)
                .put("offReason", "业务需求下线所有企鹅号")
//                .put("publishTime", strings[1])
                ;
        return objectNode;

    }
    public static void main(String[] args) throws IOException {
        resttemplate();
//        okhttp();
    }
    private static void okhttp() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.MediaType  mediaType = okhttp3.MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[{\n" +
                "  \"contentId\": 152669448,\n" +
                "  \"status\": 3,\n" +
                "  \"privateStatus\": 0,\n" +
                "  \"style\": 0,\n" +
                "  \"offReason\": \"业务需求，下线所有企鹅号\",\n" +
                "  \"subPosition\": 1,\n" +
                "  \"publishTime\": \"2020-07-06 15:33:03\"\n" +
                "}]");
        Request request = new Request.Builder()
                .url("http://discovery-cms.jd.com/discovery/exchange/content/batchUpdate.do")
                .method("POST", body)
                .addHeader("content-type", "application/json")
                .addHeader("Cookie", "sso.jd.com=BJ.8ae1def875424134bec544a5459b50d6")
                .build();
        Response response = client.newCall(request).execute();
        log.info(response.toString());
    }

    private static void resttemplate() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        RestTemplate restTemplate =new RestTemplate();


        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType( MediaType.APPLICATION_JSON);
        httpHeaders.put(HttpHeaders.COOKIE,new ArrayList<String>(Arrays.asList( "sso.jd.com=BJ.dce758e3aa8b43a09541d2b2213c9018")));



        HttpEntity<String> formEntity = null;



        formEntity = new HttpEntity<String>("[{\n" +
                "  \"contentId\": 152374652,\n" +
                "  \"status\": 3,\n" +
                "  \"privateStatus\": 0,\n" +
                "  \"style\": 0,\n" +
                "  \"offReason\": \"业务需求，下线所有企鹅号\",\n" +
                "  \"subPosition\": 1,\n" +
                "  \"publishTime\": \"2020-07-06 15:33:03\"\n" +
                "}]", httpHeaders);

        log.info("fromentity is :"+objectMapper.writeValueAsString(formEntity));


        ResponseEntity<String> exchange = restTemplate.exchange("http://discovery-cms.jd.com/discovery/exchange/content/batchUpdate.do", HttpMethod.POST, formEntity, String.class, (Object) null);

        log.info("result is :"+exchange);
    }
}
