package com.turtle.elasticsearch.seven.demo.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/21 23:47
 * FileName: EsTest_Doc_Bulk_Delete
 * Description:批量删除
 */
public class EsTest_Doc_Bulk_Delete {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new DeleteRequest().index("user").id("1001"));
            bulkRequest.add(new DeleteRequest().index("user").id("1003"));

            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("took:" + bulk.getTook());
            System.out.println("items:" + bulk.getItems());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }
}

