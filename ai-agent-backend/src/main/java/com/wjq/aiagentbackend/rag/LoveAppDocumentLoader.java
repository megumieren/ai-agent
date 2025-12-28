package com.wjq.aiagentbackend.rag;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LoveAppDocumentLoader {

    private final ResourcePatternResolver resourcePatternResolver;

    public LoveAppDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public List<Document> loadMarkdowns(){
        List<Document> allDocuments = new ArrayList<>();
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath:document/*.md");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true) //设为 true 时，Markdown 中的水平分隔符将生成新 Document 对象
                        .withIncludeCodeBlock(false) //设为 true 时，代码块将与周边文本合并到同一 Document；设为 false 时，代码块生成独立 Document 对象
                        .withIncludeBlockquote(false)//设为 true 时，引用块将与周边文本合并到同一 Document；设为 false 时，引用块生成独立 Document 对象
                        .withAdditionalMetadata("filename", filename) //允许为所有生成的 Document 对象添加自定义元数据
                        .build();
                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
                List<Document> documents = reader.get(); //文档的切片集合
                allDocuments.addAll(documents);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return allDocuments;
    }
}
