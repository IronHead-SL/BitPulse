package domain;

import java.util.List;

public record BinanceResponse(
        List<Candlestick> candlesticks
) {
}
