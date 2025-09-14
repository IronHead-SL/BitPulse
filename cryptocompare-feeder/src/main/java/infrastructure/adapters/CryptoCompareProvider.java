package infrastructure.adapters;

import domain.NewsResponse;
import infrastructure.ports.NewsDeserializerPort;
import infrastructure.ports.FetchPort;

import java.net.http.HttpClient;
import java.time.Duration;

public class CryptoCompareProvider implements FetchPort, NewsDeserializerPort {
    private final String baseUrl = "https://min-api.cryptocompare.com/data/v2/news/";
    public CryptoCompareFetcher fetcher;
    private CryptoCompareDeserializer deserializer;
    private HttpClient client;

    public CryptoCompareProvider(String apiKey) {
        this.client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        this.fetcher = new CryptoCompareFetcher(client, baseUrl, apiKey);
    }

    @Override
    public String fetch() {
        return fetcher.fetch();
    }

    @Override
    public NewsResponse deserialize(String data) {
        this.deserializer = new CryptoCompareDeserializer(data);
        return deserializer.deserialize();
    }

    public NewsResponse provideNews() {
        return deserialize(fetch());
    }
}