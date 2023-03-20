package com.turtle.elasticsearch.seven.demo.doc;

import com.alibaba.fastjson.JSONObject;
import com.turtle.elasticsearch.seven.demo.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
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
public class EsTest_Doc_Update {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            UpdateRequest updateRequest = new UpdateRequest();
            // 定位到索引+文档id
            updateRequest.index("user").id("1001");

            // 要更改的值
            updateRequest.doc(XContentType.JSON, "sex", "nv");

            // 执行更新操作
            UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);


            // 输出结果
            System.out.println("创建结果：" + update.getResult());
            System.out.println("id编号" + update.getId());


        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }

    }
}
