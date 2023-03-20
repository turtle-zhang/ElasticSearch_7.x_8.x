package com.turtle.elasticsearch.seven.demo.index;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/20 23:56
 * FileName: EsTest_Index_Delete
 * Description: 删除索引
 */
public class EsTest_Index_Delete {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("user");
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            if (delete.isAcknowledged()){
                System.out.println("索引删除成功");
            }else{
                System.out.println("索引删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (restHighLevelClient != null){
                restHighLevelClient.close();
            }
        }

    }
}
