package infrastructure.adapters.provider;

import domain.BinanceResponse;
import domain.Bitcoin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BinanceDeserializer {
    private String responseData;
    private ObjectMapper mapper;

    public BinanceDeserializer(String responseData) {
        this.responseData = responseData;
        this.mapper = new ObjectMapper();
    }

    public BinanceResponse deserialize() {
        try {
            List<Bitcoin> bitcoins = new ArrayList<>();
            JsonNode rootNode = mapper.readTree(responseData);
            for (int i = 0; i < rootNode.size(); i++) {
                JsonNode candlestick = rootNode.get(i);
                Bitcoin actualKline = new Bitcoin(
                        candlestick.get(1).asDouble(),
                        candlestick.get(2).asDouble(),
                        candlestick.get(3).asDouble(),
                        candlestick.get(4).asDouble(),
                        candlestick.get(5).asDouble(),
                        candlestick.get(7).asDouble(),
                        candlestick.get(8).asInt(),
                        candlestick.get(9).asDouble(),
                        candlestick.get(10).asDouble(),
                        Instant.ofEpochMilli(candlestick.get(0).asLong()),
                        Instant.ofEpochMilli(candlestick.get(6).asLong())
                        );
                bitcoins.add(actualKline);
            }
            return new BinanceResponse(bitcoins);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}