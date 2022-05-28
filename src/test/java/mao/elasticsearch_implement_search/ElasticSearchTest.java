package mao.elasticsearch_implement_search;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;


/**
 * Project name(项目名称)：elasticsearch_Implement_search
 * Package(包名): mao.elasticsearch_implement_search
 * Class(类名): ElasticSearchTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/5/28
 * Time(创建时间)： 20:12
 * Version(版本): 1.0
 * Description(描述)： SpringBootTest
 * <p>
 * 映射：
 * get /book/_mapping
 *
 * <pre>
 *
 * {
 *   "book" :
 *   {
 *     "mappings" :
 *     {
 *       "properties" :
 *       {
 *         "description" :
 *         {
 *           "type" : "text",
 *           "fields" :
 *           {
 *             "keyword" :
 *             {
 *               "type" : "keyword",
 *               "ignore_above" : 256
 *             }
 *           }
 *         },
 *         "name" :
 *         {
 *           "type" : "text",
 *           "fields" :
 *           {
 *             "keyword" :
 *             {
 *               "type" : "keyword",
 *               "ignore_above" : 256
 *             }
 *           }
 *         },
 *         "pic" :
 *         {
 *           "type" : "text",
 *           "fields" :
 *           {
 *             "keyword" :
 *             {
 *               "type" : "keyword",
 *               "ignore_above" : 256
 *             }
 *           }
 *         },
 *         "price" :
 *         {
 *           "type" : "float"
 *         },
 *         "query" :
 *         {
 *           "type" : "text",
 *           "fields" :
 *           {
 *             "keyword" :
 *             {
 *               "type" : "keyword",
 *               "ignore_above" : 256
 *             }
 *           }
 *         },
 *         "studymodel" :
 *         {
 *           "type" : "text",
 *           "fields" :
 *           {
 *             "keyword" :
 *             {
 *               "type" : "keyword",
 *               "ignore_above" : 256
 *             }
 *           }
 *         },
 *         "tags" :
 *         {
 *           "type" : "text",
 *           "fields" :
 *           {
 *             "keyword" :
 *             {
 *               "type" : "keyword",
 *               "ignore_above" : 256
 *             }
 *           }
 *         },
 *         "timestamp" :
 *         {
 *           "type" : "text",
 *           "fields" :
 *           {
 *             "keyword" :
 *             {
 *               "type" : "keyword",
 *               "ignore_above" : 256
 *             }
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 *
 *
 * </pre>
 * <p>
 * <p>
 * 数据：
 * get book/_search
 *
 * <pre>
 *
 * {
 *   "took" : 0,
 *   "timed_out" : false,
 *   "_shards" :
 *   {
 *     "total" : 1,
 *     "successful" : 1,
 *     "skipped" : 0,
 *     "failed" : 0
 *   },
 *   "hits" :
 *   {
 *     "total" :
 *     {
 *       "value" : 5,
 *       "relation" : "eq"
 *     },
 *     "max_score" : 1.0,
 *     "hits" :
 *     [
 *       {
 *         "_index" : "book",
 *         "_id" : "1",
 *         "_score" : 1.0,
 *         "_source" :
 *         {
 *           "name" : "Bootstrap开发",
 *           "description" : "Bootstrap是由Twitter推出的一个前台页面开发css框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长css页面开发的程序人员）轻松的实现一个css，不受浏览器限制的精美界面css效果。",
 *           "studymodel" : "201002",
 *           "price" : 38.6,
 *           "timestamp" : "2019-08-25 19:11:35",
 *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
 *           "tags" :
 *           [
 *             "bootstrap",
 *             "dev"
 *           ]
 *         }
 *       },
 *       {
 *         "_index" : "book",
 *         "_id" : "2",
 *         "_score" : 1.0,
 *         "_source" :
 *         {
 *           "name" : "java编程思想",
 *           "description" : "java语言是世界第一编程语言，在软件开发领域使用人数最多。",
 *           "studymodel" : "201001",
 *           "price" : 68.6,
 *           "timestamp" : "2019-08-25 19:11:35",
 *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
 *           "tags" :
 *           [
 *             "java",
 *             "dev"
 *           ]
 *         }
 *       },
 *       {
 *         "_index" : "book",
 *         "_id" : "3",
 *         "_score" : 1.0,
 *         "_source" :
 *         {
 *           "name" : "spring开发基础",
 *           "description" : "spring 在java领域非常流行，java程序员都在用。",
 *           "studymodel" : "201001",
 *           "price" : 78.6,
 *           "timestamp" : "2019-08-24 19:21:35",
 *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
 *           "tags" :
 *           [
 *             "spring",
 *             "java"
 *           ]
 *         }
 *       },
 *       {
 *         "_index" : "book",
 *         "_id" : "5",
 *         "_score" : 1.0,
 *         "_source" :
 *         {
 *           "name" : "java编程思想",
 *           "description" : "java语言是世界第一编程语言，在软件开发领域使用人数最多。",
 *           "studymodel" : "201001",
 *           "price" : 68.6,
 *           "timestamp" : "2022-5-25 19:11:35",
 *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
 *           "tags" :
 *           [
 *             "bootstrap",
 *             "dev"
 *           ]
 *         }
 *       },
 *       {
 *         "_index" : "book",
 *         "_id" : "6",
 *         "_score" : 1.0,
 *         "_source" :
 *         {
 *           "name" : "java编程思想",
 *           "description" : "java语言是世界第一编程语言，在软件开发领域使用人数最多。",
 *           "studymodel" : "201001",
 *           "price" : 68.6,
 *           "timestamp" : "2022-5-25 19:11:35",
 *           "pic" : "group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg",
 *           "tags" :
 *           [
 *             "bootstrap",
 *             "dev"
 *           ]
 *         }
 *       }
 *     ]
 *   }
 * }
 *
 * </pre>
 */
@SpringBootTest
public class ElasticSearchTest
{

    private static RestHighLevelClient client;

    /**
     * Before all.
     */
    @BeforeAll
    static void beforeAll()
    {
        client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));
    }

    /**
     * After all.
     *
     * @throws IOException the io exception
     */
    @AfterAll
    static void afterAll() throws IOException
    {
        client.close();
    }


    /**
     * 搜索全部
     * 请求：
     *
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query": { "match_all": {} }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：5
     *
     *
     * id:1
     * score:1.0
     * 内容：
     * ---- price：38.6
     * ---- studymodel：201002
     * ---- name：Bootstrap开发
     * ---- description：Bootstrap是由Twitter推出的一个前台页面开发css框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长css页面开发的程序人员）轻松的实现一个css，不受浏览器限制的精美界面css效果。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws IOException IOException
     */
    @Test
    void searchAll() throws IOException
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }

    }


    /**
     * 搜索全部，异步请求。
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query": { "match_all": {} }
     * }
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void searchAll_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        Thread.sleep(2000);
    }

    /**
     * 查询某字段
     * 请求内容：
     *
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match":
     *     {
     *       "description": "java"
     *     }
     *   }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：4
     *
     *
     * id:3
     * score:0.4745544
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:2
     * score:0.33773077
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:0.33773077
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:0.33773077
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询某字段
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "java"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }

    /**
     * 查询某字段，异步请求
     * 请求内容：
     *
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match":
     *     {
     *       "description": "java"
     *     }
     *   }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：4
     *
     *
     * id:3
     * score:0.4745544
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:2
     * score:0.33773077
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:0.33773077
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:0.33773077
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询某字段
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "java"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        Thread.sleep(2000);
    }


    /**
     * 搜索并排序
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match":
     *     {
     *       "description": "java"
     *     }
     *   },
     *   "sort":
     *   [
     *     {
     *       "price":
     *       {
     *         "order": "desc"
     *       }
     *     }
     *   ]
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：4
     *
     *
     * id:3
     * score:NaN
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:2
     * score:NaN
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:NaN
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:NaN
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_sort() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询某字段
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "java"));
        //排序
        searchSourceBuilder.sort("price", SortOrder.DESC);
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }

    /**
     * 搜索并排序，异步请求
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match":
     *     {
     *       "description": "java"
     *     }
     *   },
     *   "sort":
     *   [
     *     {
     *       "price":
     *       {
     *         "order": "desc"
     *       }
     *     }
     *   ]
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：4
     *
     *
     * id:3
     * score:NaN
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:2
     * score:NaN
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:NaN
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:NaN
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_sort_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询某字段
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "java"));
        //排序
        searchSourceBuilder.sort("price", SortOrder.DESC);
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }

    /**
     * 分页查询
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match_all": {}
     *   },
     *   "from": 1,
     *   "size": 2
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：5
     *
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_page() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //分页
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(2);
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * 分页查询，异步
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match_all": {}
     *   },
     *   "from": 1,
     *   "size": 2
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：5
     *
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_page_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //分页
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(2);
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }

    /**
     * 查询，返回指定的字段
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match_all": {}
     *   },
     *   "_source": ["name","price"]
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：5
     *
     *
     * id:1
     * score:1.0
     * 内容：
     * ---- price：38.6
     * ---- name：Bootstrap开发
     * ----------------------------------
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- name：java编程思想
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- name：spring开发基础
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- name：java编程思想
     * ----------------------------------
     *
     * id:6
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- name：java编程思想
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_field() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //指定某些字段
        searchSourceBuilder.fetchSource(new String[]{"name", "price"}, new String[]{});
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * 查询，返回指定的字段，异步请求
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "match_all": {}
     *   },
     *   "_source": ["name","price"]
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：5
     *
     *
     * id:1
     * score:1.0
     * 内容：
     * ---- price：38.6
     * ---- name：Bootstrap开发
     * ----------------------------------
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- name：java编程思想
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- name：spring开发基础
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- name：java编程思想
     * ----------------------------------
     *
     * id:6
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- name：java编程思想
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_field_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //指定某些字段
        searchSourceBuilder.fetchSource(new String[]{"name", "price"}, new String[]{});
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * 多搜索条件
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "bool":
     *     {
     *       "must":
     *       [
     *         {
     *           "match":
     *           {
     *             "name": "编程"
     *           }
     *         }
     *       ],
     *       "should":
     *       [
     *         {
     *           "match":
     *           {
     *             "name": "spring开发基础"
     *           }
     *         }
     *       ]
     *     }
     *   }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     *
     *
     * id:2
     * score:1.0409627
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:1.0409627
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.0409627
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_multipleConditions() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.boolQuery().
                must(QueryBuilders.matchQuery("name", "编程")).
                should(QueryBuilders.matchQuery("name", "spring开发基础")));

        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * 多搜索条件，异步请求
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *   "query":
     *   {
     *     "bool":
     *     {
     *       "must":
     *       [
     *         {
     *           "match":
     *           {
     *             "name": "编程"
     *           }
     *         }
     *       ],
     *       "should":
     *       [
     *         {
     *           "match":
     *           {
     *             "name": "spring开发基础"
     *           }
     *         }
     *       ]
     *     }
     *   }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     *
     *
     * id:2
     * score:1.0409627
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:1.0409627
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.0409627
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_multipleConditions_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.boolQuery().
                must(QueryBuilders.matchQuery("name", "编程")).
                should(QueryBuilders.matchQuery("name", "spring开发基础")));

        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * multi_match
     * 搜索在多个字段下是否包含某关键字
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "multi_match":
     *       {
     *         "query": "语言",
     *         "fields": ["name","description"]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：1.6503837
     *
     *
     * id:2
     * score:1.6503837
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:1.6503837
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.6503837
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_multi_match() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("语言", "name", "description"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * multi_match ,异步请求
     * 搜索在多个字段下是否包含某关键字
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "multi_match":
     *       {
     *         "query": "语言",
     *         "fields": ["name","description"]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：1.6503837
     *
     *
     * id:2
     * score:1.6503837
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:1.6503837
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.6503837
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_multi_match_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("语言", "name", "description"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * range query
     * 范围查询
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "range":
     *       {
     *         "price":
     *         {
     *           "gte": 69.2,
     *           "lte": 80
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：1
     * 最大分数：1.0
     *
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_range_query() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.rangeQuery("price").gte(69.2).lte(80));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * range query ,异步请求
     * 范围查询
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "range":
     *       {
     *         "price":
     *         {
     *           "gte": 69.2,
     *           "lte": 80
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：1
     * 最大分数：1.0
     *
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_range_query_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.rangeQuery("price").gte(69.2).lte(80));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * term query
     * 字段为keyword时，存储和搜索都不分词
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "term":
     *       {
     *         "description":
     *         {
     *           "value": "java程序员"
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：0
     * 最大分数：NaN
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_term_query() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.termQuery("description", "java程序员"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * term query ,异步请求
     * 字段为keyword时，存储和搜索都不分词
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "term":
     *       {
     *         "description":
     *         {
     *           "value": "java程序员"
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：0
     * 最大分数：NaN
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_term_query_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.termQuery("description", "java程序员"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * terms query
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "terms":
     *       {
     *         "description":
     *         [
     *           "spring",
     *           "第一编程语言"
     *         ]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：1
     * 最大分数：1.0
     *
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_terms_query() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.termsQuery("description", "spring", "第一编程语言"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * terms query ,异步请求
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "terms":
     *       {
     *         "description":
     *         [
     *           "spring",
     *           "第一编程语言"
     *         ]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：1
     * 最大分数：1.0
     *
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_terms_query_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.termsQuery("description", "spring", "第一编程语言"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * exist query
     * 查询有某些字段值的文档
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "exists":
     *       {
     *         "field": "tags"
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：5
     * 最大分数：1.0
     *
     *
     * id:1
     * score:1.0
     * 内容：
     * ---- price：38.6
     * ---- studymodel：201002
     * ---- name：Bootstrap开发
     * ---- description：Bootstrap是由Twitter推出的一个前台页面开发css框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长css页面开发的程序人员）轻松的实现一个css，不受浏览器限制的精美界面css效果。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_exist_query() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.existsQuery("tags"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * exist query ，异步请求
     * 查询有某些字段值的文档
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "exists":
     *       {
     *         "field": "tags"
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：5
     * 最大分数：1.0
     *
     *
     * id:1
     * score:1.0
     * 内容：
     * ---- price：38.6
     * ---- studymodel：201002
     * ---- name：Bootstrap开发
     * ---- description：Bootstrap是由Twitter推出的一个前台页面开发css框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长css页面开发的程序人员）轻松的实现一个css，不受浏览器限制的精美界面css效果。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_exist_query_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.existsQuery("tags"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * Fuzzy query
     * 返回包含与搜索词类似的词的文档，该词由Levenshtein编辑距离度量。
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "fuzzy":
     *       {
     *         "name":
     *         {
     *           "value": "jaav"
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：0.39036104
     *
     *
     * id:2
     * score:0.39036104
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:0.39036104
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:0.39036104
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_Fuzzy_query() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery("name", "jaav"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * Fuzzy query ，异步请求
     * 返回包含与搜索词类似的词的文档，该词由Levenshtein编辑距离度量。
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "fuzzy":
     *       {
     *         "name":
     *         {
     *           "value": "jaav"
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：0.39036104
     *
     *
     * id:2
     * score:0.39036104
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:0.39036104
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:0.39036104
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_Fuzzy_query_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery("name", "jaav"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * IDs
     * 查询多个id为某个数的结果
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "ids":
     *       {
     *         "values": ["1","5","3","100"]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：1.0
     *
     *
     * id:1
     * score:1.0
     * 内容：
     * ---- price：38.6
     * ---- studymodel：201002
     * ---- name：Bootstrap开发
     * ---- description：Bootstrap是由Twitter推出的一个前台页面开发css框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长css页面开发的程序人员）轻松的实现一个css，不受浏览器限制的精美界面css效果。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_IDs() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("1", "5", "3", "100"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * IDs ，异步请求
     * 查询多个id为某个数的结果
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "ids":
     *       {
     *         "values": ["1","5","3","100"]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：1.0
     *
     *
     * id:1
     * score:1.0
     * 内容：
     * ---- price：38.6
     * ---- studymodel：201002
     * ---- name：Bootstrap开发
     * ---- description：Bootstrap是由Twitter推出的一个前台页面开发css框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长css页面开发的程序人员）轻松的实现一个css，不受浏览器限制的精美界面css效果。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_IDs_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("1", "5", "3", "100"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * prefix
     * 查询某字段满足某前缀的所有数据
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "prefix":
     *       {
     *         "description":
     *         {
     *           "value": "sprin"
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：1
     * 最大分数：1.0
     *
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception
     */
    @Test
    void search_prefix() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.prefixQuery("description", "sprin"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * prefix ，异步请求
     * 查询某字段满足某前缀的所有数据
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "prefix":
     *       {
     *         "description":
     *         {
     *           "value": "sprin"
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：1
     * 最大分数：1.0
     *
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception
     */
    @Test
    void search_prefix_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.prefixQuery("description", "sprin"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * regexp query
     * 正则查询
     * 查询某字段满足某正则表达式的所有数据
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "regexp":
     *       {
     *         "description":
     *         {
     *           "value": "j.*a",
     *           "flags": "ALL"
     *         }
     *       }
     *     }
     * }
     *
     * 结果：
     * <pre>
     *
     * 总数量：4
     * 最大分数：1.0
     *
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * </pre>
     *
     * @throws Exception
     */
    @Test
    void search_regexp_query() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.regexpQuery("description", "j.*a"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * regexp query ，异步请求
     * 正则查询
     * 查询某字段满足某正则表达式的所有数据
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "regexp":
     *       {
     *         "description":
     *         {
     *           "value": "j.*a",
     *           "flags": "ALL"
     *         }
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：4
     * 最大分数：1.0
     *
     *
     * id:2
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:3
     * score:1.0
     * 内容：
     * ---- price：78.6
     * ---- studymodel：201001
     * ---- name：spring开发基础
     * ---- description：spring 在java领域非常流行，java程序员都在用。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-24 19:21:35
     * ---- tags：[spring, java]
     * ----------------------------------
     *
     * id:5
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:1.0
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception
     */
    @Test
    void search_regexp_query_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.regexpQuery("description", "j.*a"));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }


    /**
     * Filter
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "bool":
     *       {
     *         "must":
     *         [
     *         {
     *           "match":
     *           {
     *             "description": "java程序员"
     *           }
     *         }
     *       ],
     *       "filter":
     *       [
     *         {
     *           "range":
     *           {
     *             "price":
     *             {
     *               "gte": 60,
     *               "lte": 70
     *             }
     *           }
     *         }
     *       ]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：0.4398797
     *
     *
     * id:2
     * score:0.4398797
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:0.4398797
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:0.4398797
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_Filter() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("description", "java程序员"))
                .filter(QueryBuilders.rangeQuery("price").gte(60).lte(70)));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        //获取数据
        SearchHits hits = searchResponse.getHits();
        //总数
        long value = hits.getTotalHits().value;
        System.out.println("总数量：" + value);
        float maxScore = hits.getMaxScore();
        System.out.println("最大分数：" + maxScore);
        System.out.println();
        SearchHit[] hitsHits = hits.getHits();
        //遍历数据
        for (SearchHit hitsHit : hitsHits)
        {
            System.out.println();
            //获得id
            String id = hitsHit.getId();
            //获得得分
            float score = hitsHit.getScore();
            //获得内容
            Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

            //打印内容
            System.out.println("id:" + id);
            System.out.println("score:" + score);
            System.out.println("内容：");
            for (String key : sourceAsMap.keySet())
            {
                System.out.println("---- " + key + "：" + sourceAsMap.get(key));
            }
            System.out.println("----------------------------------");
        }
    }


    /**
     * Filter ，异步请求
     * <p>
     * 请求内容：
     * <pre>
     *
     * GET /book/_search
     * {
     *     "query":
     *     {
     *       "bool":
     *       {
     *         "must":
     *         [
     *         {
     *           "match":
     *           {
     *             "description": "java程序员"
     *           }
     *         }
     *       ],
     *       "filter":
     *       [
     *         {
     *           "range":
     *           {
     *             "price":
     *             {
     *               "gte": 60,
     *               "lte": 70
     *             }
     *           }
     *         }
     *       ]
     *       }
     *     }
     * }
     *
     * </pre>
     * <p>
     * 结果：
     * <pre>
     *
     * 总数量：3
     * 最大分数：0.4398797
     *
     *
     * id:2
     * score:0.4398797
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2019-08-25 19:11:35
     * ---- tags：[java, dev]
     * ----------------------------------
     *
     * id:5
     * score:0.4398797
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * id:6
     * score:0.4398797
     * 内容：
     * ---- price：68.6
     * ---- studymodel：201001
     * ---- name：java编程思想
     * ---- description：java语言是世界第一编程语言，在软件开发领域使用人数最多。
     * ---- pic：group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg
     * ---- timestamp：2022-5-25 19:11:35
     * ---- tags：[bootstrap, dev]
     * ----------------------------------
     *
     * </pre>
     *
     * @throws Exception Exception
     */
    @Test
    void search_Filter_async() throws Exception
    {
        //构建搜索请求
        SearchRequest searchRequest = new SearchRequest("book");
        //构建请求体
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("description", "java程序员"))
                .filter(QueryBuilders.rangeQuery("price").gte(60).lte(70)));
        //放入请求中
        searchRequest.source(searchSourceBuilder);
        //发起异步请求
        client.searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>()
        {
            /**
             * 成功的回调
             *
             * @param searchResponse SearchResponse
             */
            @Override
            public void onResponse(SearchResponse searchResponse)
            {
                //获取数据
                SearchHits hits = searchResponse.getHits();
                //总数
                long value = hits.getTotalHits().value;
                System.out.println("总数量：" + value);
                float maxScore = hits.getMaxScore();
                System.out.println("最大分数：" + maxScore);
                System.out.println();
                SearchHit[] hitsHits = hits.getHits();
                //遍历数据
                for (SearchHit hitsHit : hitsHits)
                {
                    System.out.println();
                    //获得id
                    String id = hitsHit.getId();
                    //获得得分
                    float score = hitsHit.getScore();
                    //获得内容
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();

                    //打印内容
                    System.out.println("id:" + id);
                    System.out.println("score:" + score);
                    System.out.println("内容：");
                    for (String key : sourceAsMap.keySet())
                    {
                        System.out.println("---- " + key + "：" + sourceAsMap.get(key));
                    }
                    System.out.println("----------------------------------");
                }
            }

            /**
             * 失败的回调
             *
             * @param e Exception
             */
            @Override
            public void onFailure(Exception e)
            {
                e.printStackTrace();
            }
        });
        //休眠2秒
        Thread.sleep(2000);
    }
}
