package com.turtle.elasticsearch.seven.demo.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
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
public class EsTest_Doc_Get {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            GetRequest getRequest = new GetRequest();
            // 定位到索引+文档id
            getRequest.index("user").id("1001");

            // 执行查询操作
            GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);


            // 输出结果
            System.out.println("创建结果：" + documentFields.getSource());
            System.out.println("id编号" + documentFields.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }

    }
}
