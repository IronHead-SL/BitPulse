package infrastructure.adapters;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

public class BinanceFetcher {
    private final HttpClient client;
    private final HttpRequest request;
    private String interval = "1m";
    private String startTime = "1504220400000";
    private String endTime = String.valueOf(Instant.now().getEpochSecond() * 1000);

    public BinanceFetcher(HttpClient client, String baseUrl) {
        this.client = client;
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "&interval=" + interval + "&startTime=" + startTime + "&endTime=" + endTime + "&limit=1000"))
                .timeout(Duration.ofMinutes(1))
                .GET()
                .build();
    }

    public String fetch() {
        try {
            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response != null && response.statusCode() == 200) return response.body();
            else throw new RuntimeException("Failed to fetch news: " + (response != null ? response.statusCode() : "No response"));
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }

    public String getInterval() {return interval;}

    public void setInterval(String interval) {this.interval = interval;}

    public String getStartTime() {return startTime;}

    public void setStartTime(String startTime) {this.startTime = startTime;}

    public String getEndTime() {return endTime;}

    public void setEndTime(String endTime) {this.endTime = endTime;}
}
