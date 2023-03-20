package com.turtle.elasticsearch.seven.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/16 23:53
 * FileName: EsTest_Index_Get
 * Description: 查看索引信息
 */
public class EsTest_Index_Get {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
            );

            // 查询索引的request请求
            GetIndexRequest getIndexRequest = new GetIndexRequest("user");
            GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
            System.out.println("Setting:" + getIndexResponse.getSettings());
            System.out.println("Indices:" + getIndexResponse.getIndices());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }
}
