package infrastructure.adapters;

import domain.NewsResponse;
import domain.Article;

import java.io.*;
import java.util.List;
import java.util.concurrent.*;

public class ContinuousNewsFetcher {
    private final CryptoCompareProvider provider;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private long lastTimestamp;
    private final String checkpointFile = "last_news_timestamp.txt";

    public ContinuousNewsFetcher(CryptoCompareProvider provider) {
        this.provider = provider;
        this.lastTimestamp = loadLastTimestamp();
    }

    public void start() {
        scheduler.scheduleAtFixedRate(this::fetchAndSave, 0, 5, TimeUnit.MINUTES);
    }

    public void stop() {
        scheduler.shutdown();
        saveLastTimestamp();
    }

    private void fetchAndSave() {
        try {
            provider.fetcher.setLTs(String.valueOf(lastTimestamp));
            NewsResponse response = provider.provideNews();
            if (response.data() != null && !response.data().isEmpty()) {
                System.out.println("Fetched " + response.data().size() + " news articles");
                // todo: send to broker
                processArticles(response.data());
                long newestTimestamp = response.data().stream()
                        .mapToLong(Article::getPublishedOn)
                        .max()
                        .orElse(lastTimestamp);
                if (newestTimestamp > lastTimestamp) {
                    lastTimestamp = newestTimestamp;
                    saveLastTimestamp();
                    System.out.println("Updated last timestamp to: " + lastTimestamp);
                }
            } else System.out.println("No new articles found");
        } catch (Exception e) {
            System.err.println("Error fetching news: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processArticles(List<Article> articles) {
        for (Article article : articles) {
            System.out.println("Title: " + article.getTitle());
            System.out.println("Published: " + article.getPublishedOn());
            System.out.println("Source: " + article.getSource());
            System.out.println("URL: " + article.getUrl());
            System.out.println("---");
        }
    }

    private long loadLastTimestamp() {
        try (BufferedReader reader = new BufferedReader(new FileReader(checkpointFile))) {
            return Long.parseLong(reader.readLine());
        } catch (Exception e) {return 1504224000L;}
    }

    private void saveLastTimestamp() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(checkpointFile))) {
            writer.write(String.valueOf(lastTimestamp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStartTimestamp(long timestamp) {
        this.lastTimestamp = timestamp;
        saveLastTimestamp();
    }

    public long getCurrentTimestamp() {
        return lastTimestamp;
    }
}