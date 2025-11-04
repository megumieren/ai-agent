package com.wjq.aiagentbackend.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HttpAiInvoke {
    public static void main(String[] args) {
        // 从环境变量获取 API Key
        String apiKey = TestApiKey.API_KEY;
        
        // 构建请求 JSON 数据
        JSONObject requestData = new JSONObject();
        requestData.set("model", "qwen-plus");
        
        JSONObject input = new JSONObject();
        JSONArray messages = JSONUtil.createArray();
        
        JSONObject systemMessage = new JSONObject();
        systemMessage.set("role", "system");
        systemMessage.set("content", "You are a helpful assistant.");
        messages.add(systemMessage);
        
        JSONObject userMessage = new JSONObject();
        userMessage.set("role", "user");
        userMessage.set("content", "你是谁？");
        messages.add(userMessage);
        
        input.set("messages", messages);
        requestData.set("input", input);
        
        JSONObject parameters = new JSONObject();
        parameters.set("result_format", "message");
        requestData.set("parameters", parameters);
        
        // 发送 HTTP 请求
        HttpResponse response = HttpRequest.post("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestData.toString())
                .execute();
        
        // 输出响应结果
        System.out.println("响应状态: " + response.getStatus());
        System.out.println("响应内容: " + response.body());
    }
}