package com.turtle.elasticsearch.seven.demo.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/21 0:05
 * FileName: EsTest_Doc_Create
 * Description:创建文本对象
 */
public class EsTest_Doc_Delete {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

            DeleteRequest deleteRequest = new DeleteRequest();
            // 定位到索引+文档id
            deleteRequest.index("user").id("1001");

            // 执行删除操作
            DeleteResponse documentFields = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

            // 输出结果
            System.out.println("创建结果：" + documentFields.getResult());
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
