package domain;

import java.time.Instant;

public class Article {
    private final String guid;
    private final long publishedOn;
    private final String title;
    private final String url;
    private final String source;
    private final String tags;
    private final Instant ts = Instant.now();
    private final String ss = "news-api";

    public Article(String guid, long publishedOn, String title,
                   String url, String source, String tags) {
        this.guid = guid;
        this.publishedOn = publishedOn;
        this.title = title;
        this.url = url;
        this.source = source;
        this.tags = tags;
    }

    public String getGuid() { return guid; }
    public long getPublishedOn() { return publishedOn; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public String getSource() { return source; }
    public String getTags() { return tags; }
    public Instant getTs() { return ts; }
    public String getSs() { return ss; }
}
