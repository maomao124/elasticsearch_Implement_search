package mao.elasticsearch_implement_search;

import org.apache.http.HttpHost;
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


}