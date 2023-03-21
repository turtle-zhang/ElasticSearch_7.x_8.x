package com.turtle.elasticsearch.seven.demo.doc;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turtle.elasticsearch.seven.demo.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/21 0:05
 * FileName: EsTest_Doc_Create
 * Description:创建文本对象
 */
public class EsTest_Doc_Create {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            IndexRequest indexRequest = new IndexRequest();
            indexRequest.index("user").id("1001");

            User user = new User();
            user.setAge(10);
            user.setName("张三");
            user.setSex("男");

            String userStr = JSONObject.toJSONString(user);

            indexRequest.source(userStr, XContentType.JSON);

            IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);


            // 输出结果
            System.out.println("创建结果：" + index.getResult());
            System.out.println("id编号" + index.getId());


        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }

    }
}
