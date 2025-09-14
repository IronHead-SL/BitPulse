package infrastructure.adapters;

import domain.BinanceResponse;
import domain.Candlestick;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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
            List<Candlestick> candlesticks = new ArrayList<>();
            JsonNode rootNode = mapper.readTree(responseData);
            for (int i = 0; i < rootNode.size(); i++) {
                JsonNode candlestick = rootNode.get(i);
                Candlestick actualKline = new Candlestick(
                    candlestick.get(0).asLong(),
                    candlestick.get(1).asDouble(),
                    candlestick.get(2).asDouble(),
                    candlestick.get(3).asDouble(),
                    candlestick.get(4).asDouble(),
                    candlestick.get(5).asDouble(),
                    candlestick.get(6).asLong(),
                    candlestick.get(7).asDouble(),
                    candlestick.get(8).asInt(),
                    candlestick.get(9).asDouble(),
                    candlestick.get(10).asDouble()
                );
                candlesticks.add(actualKline);
            }
            return new BinanceResponse(candlesticks);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}