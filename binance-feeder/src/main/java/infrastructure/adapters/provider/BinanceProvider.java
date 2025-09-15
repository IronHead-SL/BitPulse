package infrastructure.adapters.provider;

import domain.BinanceResponse;
import infrastructure.ports.DeserializerPort;
import infrastructure.ports.FetchPort;

import java.net.http.HttpClient;
import java.time.Duration;

public class BinanceProvider implements FetchPort, DeserializerPort {
    private final String baseUrl = "https://api.binance.com/api/v3/klines?symbol=BTCUSDT";
    public BinanceFetcher fetcher;
    private BinanceDeserializer deserializer;
    private HttpClient client;

    public BinanceProvider() {
        this.client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        this.fetcher = new BinanceFetcher(client, baseUrl);
    }

    @Override
    public String fetch() {
        return fetcher.fetch();
    }

    @Override
    public BinanceResponse deserialize(String data) {
        this.deserializer = new BinanceDeserializer(data);
        return deserializer.deserialize();
    }

    public BinanceResponse provideNews() {
        return deserialize(fetch());
    }
}