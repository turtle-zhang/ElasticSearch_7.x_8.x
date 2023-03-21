package com.turtle.elasticsearch.seven.demo.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Iterator;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/21 23:34
 * FileName: EsTest_Doc_Bulk_Create
 * Description: 批量创建文档
 */
public class EsTest_Doc_Bulk_Create {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));
            BulkRequest bulkRequest = new BulkRequest();

            // bulkRequest.add 可以添加不同类型的request对象
            bulkRequest.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON,"name","zhangsan","age",10));
            bulkRequest.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name","lisi","age",20));
            bulkRequest.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","wagnwu","age",30));
            bulkRequest.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name","zhaoliu","age",10));
            bulkRequest.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON,"name","liqi","age",40));

            // 通过命令 restHighLevelClient.bulk  来执行批量命令
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            System.out.println("took:"+bulk.getTook());
            System.out.println("items:"+bulk.getItems());
            // 获取响应对象信息
            Iterator<BulkItemResponse> iterator = bulk.iterator();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }
}
