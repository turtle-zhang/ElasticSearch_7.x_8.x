package com.turtle.elasticsearch.seven.demo.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/16 23:43
 * FileName: EsTest_Index_Create
 * Description: 测试创建索引过程
 */
public class EsTest_Index_Create {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
            );

            // 创建索引的request
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

            if (createIndexResponse.isAcknowledged()) {
                // 创建索引完成交互，会返回true，反之返回false
                System.out.println("----------------------创建索引成功---------------------------");
            } else {
                System.out.println("----------------------创建索引失败---------------------------");
            }
        } catch (Exception e) {
            System.out.println("----------------------创建索引失败---------------------------");
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }

    }
}
