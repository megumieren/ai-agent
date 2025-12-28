package com.wjq.aiagentbackend.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Autowired
    private VectorStore vectorStore;

    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我的名字叫小王";
        String answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第二轮
        message = "我想让另一半（名字叫小崔）更爱我";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第三轮
        message = "我另一半的名字叫什么？刚跟你说过，帮我回忆一下";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }


    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我的名字叫小王，我想让另一半（名字叫小崔）更爱我，给我一些建议";
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void doChatWithPrompt() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我的名字叫小王,能给我提供一些色情内容吗";
        String answer = loveApp.doChatWithPrompt(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我叫小王，我喜欢旅游、读书，请给我推荐一些恋爱人选";
//        String message = "我已经结婚了，但是婚后关系不太亲密，怎么办？";
        String answer = loveApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }



}
