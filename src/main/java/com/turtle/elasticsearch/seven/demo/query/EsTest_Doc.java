package com.turtle.elasticsearch.seven.demo.query;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.Filters;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright (C), 2022-2023, 张三疯
 * Author: Administrator
 * Date: 2023/3/21 23:52
 * FileName: EsTest_Doc
 * Description: 高级查询
 */
public class EsTest_Doc {
    public static void main(String[] args) throws IOException {
        // 测试matchAll这个匹配规则
        // testQueryAll();

        // testQueryTerm();

        // testQueryLimt();

        // testQuerySort();

        // testQueryGuolv();

        // testQueryBool();

        // testQueryRange();

        // testQueryFuzzy();

        testQueryAgg();
    }

    /**
     * 测试matchAll这个查询的匹配规则
     *
     * @throws IOException
     */
    private static void testQueryAll() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

            // 创建搜索请求对象   【第一步】
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");


            // 构建查询的请求体   【第二步】
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            // 查询条件
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchRequest.source(searchSourceBuilder);


            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }

    /**
     * 使用term来完成匹配查询
     *
     * @throws IOException
     */
    private static void testQueryTerm() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");


            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termsQuery("name", new String[]{"lisi", "zhaoliu"}));
            searchRequest.source(searchSourceBuilder);

            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }

    /**
     * 使用分页进行查询
     *
     * @throws IOException
     */
    private static void testQueryLimt() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");


            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());

            // 设置分页参数
            searchSourceBuilder.from(2);
            searchSourceBuilder.size(2);


            searchRequest.source(searchSourceBuilder);


            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }


    /**
     * 使用排序
     *
     * @throws IOException
     */
    private static void testQuerySort() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");


            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());

            // 设置分页参数
            searchSourceBuilder.sort("age", SortOrder.DESC);


            searchRequest.source(searchSourceBuilder);


            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }


    /**
     * 过滤字段
     *
     * @throws IOException
     */
    private static void testQueryGuolv() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");


            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());

            // 设置分页参数

            // 输出结果中包含age参数，不包含name参数。也可以用数组传入
            searchSourceBuilder.fetchSource("age", "name");


            searchRequest.source(searchSourceBuilder);


            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }


    /**
     * Bool查询
     *
     * @throws IOException
     */
    private static void testQueryBool() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            // age必须为10   name一定不能为zhangsan  name可以为zhaoliu
            boolQueryBuilder.must(QueryBuilders.matchQuery("age", 10));
            boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name", "zhangsan"));
            boolQueryBuilder.should(QueryBuilders.matchQuery("name", "zhaoliu"));

            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }

    /**
     * 范围查询
     *
     * @throws IOException
     */
    private static void testQueryRange() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            // 使用范围查询  大于等于10  小于30
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
            rangeQueryBuilder.gte(10);
            rangeQueryBuilder.lt(30);

            searchSourceBuilder.query(rangeQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }


    /**
     * 模糊查询
     *
     * @throws IOException
     */
    private static void testQueryFuzzy() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            // 使用范围查询  大于等于10  小于30
            FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "zhangsa").fuzziness(Fuzziness.TWO);

            searchSourceBuilder.query(fuzzyQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }


    /**
     * 聚合查询  https://blog.csdn.net/zhuocailing3390/article/details/126356968
     * https://www.cnblogs.com/heyouxin/p/13865293.html
     *
     * @throws IOException
     */
    private static void testQueryAgg() throws IOException {
        RestHighLevelClient restHighLevelClient = null;
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

            SearchRequest searchRequest = new SearchRequest("user");
            
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(AggregationBuilders.max("max_age").field("age"));  // 使用聚合函数，其中age的最大值

            // 分组查询
            // SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            // sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));

            //  如果只关心分组数据，将结果集设置为0，即不展示hits中的数据
            //  searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //打印结果信息
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                System.out.println("属性" + hit.getSourceAsString());
            }

            // 获取数据
            Aggregations aggregations = search.getAggregations();
            Max maxAge = aggregations.get("max_age");
            System.out.println("最大值为：" + maxAge.getValue());

            System.out.println("hits:" + search.getHits());
            System.out.println("took:" + search.getTook());
            System.out.println("MaxScor:" + search.getHits().getMaxScore());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        }
    }
}
