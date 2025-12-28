package com.wjq.aiagentbackend;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AiAgentApplicationTests {

    @Resource
    private VectorStore vectorStore;

    @Test
    public void reserve(){
        int[] nums = {3,2,1};
        int[] newArray = new int[nums.length];
        int temp = nums.length;
        for(int i = 0;i < nums.length;i++){
            newArray[i] = nums[temp - 1];
            temp--;
        }
        nums = newArray;
        System.out.println(nums[0]);
        HashSet hashSet = new HashSet();
    }

    @Test
    public void test(){
        List<Document> documents = List.of(
                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
                new Document("The World is Big and Salvation Lurks Around the Corner"),
                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));

// Add the documents to PGVector
        vectorStore.add(documents);

        // Retrieve documents similar to a query
        List<Document> results = this.vectorStore.similaritySearch(SearchRequest.builder().query("Spring").topK(5).build());
    }


}
