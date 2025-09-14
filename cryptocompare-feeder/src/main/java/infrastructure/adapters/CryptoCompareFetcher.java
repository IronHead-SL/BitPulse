package infrastructure.adapters;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class CryptoCompareFetcher {
    private final HttpClient client;
    private final String baseUrl;
    private String apiKey;
    private String lang = "EN";
    private String lTs;
    private String categories = "";
    private int limit = 50;

    public CryptoCompareFetcher(HttpClient client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
        this.lTs = "1504224000";
    }

    public CryptoCompareFetcher(HttpClient client, String baseUrl, String apiKey) {
        this(client, baseUrl);
        this.apiKey = apiKey;
    }

    public String fetch() {
        try {
            String url = buildUrl();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMinutes(1))
                    .GET()
                    .build();
            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response != null && response.statusCode() == 200) return response.body();
            else throw new RuntimeException("Failed to fetch news: " + (response != null ? response.statusCode() : "No response"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUrl() {
        StringBuilder url = new StringBuilder(baseUrl);
        url.append("?lang=").append(lang);
        if (apiKey != null && !apiKey.isEmpty()) url.append("&api_key=").append(apiKey);
        if (lTs != null && !lTs.isEmpty()) url.append("&lTs=").append(lTs);
        if (categories != null && !categories.isEmpty()) {url.append("&categories=").append(categories);}
        return url.toString();
    }

    public String getLTs() { return lTs; }
    public void setLTs(String lTs) { this.lTs = lTs; }
    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }
    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }
}