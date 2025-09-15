package infrastructure.adapters.provider;

import domain.BinanceResponse;
import domain.Bitcoin;

import java.io.*;
import java.time.Instant;
import java.util.concurrent.*;

public class ContinuousBinanceFetcher {
    private final BinanceProvider provider;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private long candlestickCloseTime;
    private final String checkpointFile = "last_kline.txt";

    public ContinuousBinanceFetcher(BinanceProvider provider) {
        this.provider = provider;
        this.candlestickCloseTime = loadLastKline();
    }

    public void start() {
        scheduler.scheduleAtFixedRate(this::fetchAndSave, 0, 1, TimeUnit.MINUTES);
    }

    public void stop() {
        scheduler.shutdown();
        saveLastKline();
    }

    private void fetchAndSave() {
        try {
            provider.fetcher.setStartTime(String.valueOf(candlestickCloseTime + 1));
            provider.fetcher.setEndTime(String.valueOf(Instant.now().toEpochMilli()));
            BinanceResponse response = provider.provideNews();
            if (!response.bitcoins().isEmpty()) {
                Bitcoin last = response.bitcoins().get(response.bitcoins().size() - 1);
                candlestickCloseTime = last.getCandlestickCloseTime().toEpochMilli();
                saveLastKline();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long loadLastKline() {
        try (BufferedReader reader = new BufferedReader(new FileReader(checkpointFile))) {
            return Long.parseLong(reader.readLine());
        } catch (Exception e) {
            return 1504220400000L;
        }
    }

    private void saveLastKline() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(checkpointFile))) {
            writer.write(String.valueOf(candlestickCloseTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}