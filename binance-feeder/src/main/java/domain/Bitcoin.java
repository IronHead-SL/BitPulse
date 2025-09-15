package domain;

import java.time.Instant;

public class Bitcoin {
        private final double openPrice;
        private final double highPrice;
        private final double lowPrice;
        private final double closePrice;
        private final double volume;
        private final double quoteAssetVolume;
        private final int numberOfTrades;
        private final double takerBuyBaseAssetVolume;
        private final double takerBuyQuoteAssetVolume;
        private final Instant candlestickOpenTime;
        private final Instant candlestickCloseTime;
        private final Instant ts = Instant.now();
        private final String ss = "Binance";

    public Bitcoin(double openPrice, double highPrice, double lowPrice, double closePrice, double volume, double quoteAssetVolume,
                   int numberOfTrades, double takerBuyBaseAssetVolume, double takerBuyQuoteAssetVolume, Instant candlestickOpenTime,
                   Instant candlestickCloseTime) {
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
        this.candlestickOpenTime = candlestickOpenTime;
        this.candlestickCloseTime = candlestickCloseTime;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getVolume() {
        return volume;
    }

    public double getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    public double getTakerBuyBaseAssetVolume() {
        return takerBuyBaseAssetVolume;
    }

    public double getTakerBuyQuoteAssetVolume() {
        return takerBuyQuoteAssetVolume;
    }

    public Instant getCandlestickOpenTime() {
        return candlestickOpenTime;
    }

    public Instant getCandlestickCloseTime() {
        return candlestickCloseTime;
    }

    public Instant getTs() {
        return ts;
    }

    public String getSs() {
        return ss;
    }
}
