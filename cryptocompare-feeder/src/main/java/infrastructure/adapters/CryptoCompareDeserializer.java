package infrastructure.adapters;

import domain.NewsResponse;
import domain.Article;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CryptoCompareDeserializer {
    private String responseData;
    private ObjectMapper mapper;

    public CryptoCompareDeserializer(String responseData) {
        this.responseData = responseData;
        this.mapper = new ObjectMapper();
    }

    public NewsResponse deserialize() {
        try {
            JsonNode rootNode = mapper.readTree(responseData);
            List<Article> articles = new ArrayList<>();
            JsonNode dataNode = rootNode.path("Data");
            if (dataNode.isArray()) {
                for (JsonNode articleNode : dataNode) {
                    Article article = parseArticle(articleNode);
                    articles.add(article);
                }
            }
            return new NewsResponse(articles);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

    private Article parseArticle(JsonNode articleNode) {
        return new Article(
                articleNode.path("guid").asText(),
                articleNode.path("published_on").asLong(),
                articleNode.path("title").asText(),
                articleNode.path("url").asText(),
                articleNode.path("source").asText(),
                articleNode.path("tags").asText()
        );
    }
}