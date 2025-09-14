package domain;

import java.time.Instant;

public class Candlestick {
        private final long klineOpenTime;
        private final double openPrice;
        private final double highPrice;
        private final double lowPrice;
        private final double closePrice;
        private final double volume;
        private final long klineCloseTime;
        private final double quoteAssetVolume;
        private final int numberOfTrades;
        private final double takerBuyBaseAssetVolume;
        private final double takerBuyQuoteAssetVolume;
        private final Instant ts = Instant.now();
        private final String ss = "news-api";

    public Candlestick(long klineOpenTime, double openPrice, double highPrice, double lowPrice, double closePrice, double volume, long klineCloseTime, double quoteAssetVolume, int numberOfTrades, double takerBuyBaseAssetVolume, double takerBuyQuoteAssetVolume) {
        this.klineOpenTime = klineOpenTime;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.klineCloseTime = klineCloseTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    public long getKlineOpenTime() {
        return klineOpenTime;
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

    public long getKlineCloseTime() {
        return klineCloseTime;
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

    public Instant getTs() {
        return ts;
    }

    public String getSs() {
        return ss;
    }
}
