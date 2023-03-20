package com.turtle.elasticsearch.seven.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Copyright (C), 2022-2023, 张三疯
 *
 * @Author: Administrator
 * Date: 2023/3/16 23:37
 * FileName: EsTest_Client
 * Description: 测试创建ES的客户端
 */
public class EsTest_Client {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            // 使用RestHighLevelClient创建一个连接
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9920, "http"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 在创建了连接后需要进行关闭操作
            restHighLevelClient.close();
        }
    }
}
