package infrastructure.adapters;

import domain.BinanceResponse;
import domain.Candlestick;

import java.io.*;
import java.time.Instant;
import java.util.concurrent.*;

public class ContinuousBinanceFetcher {
    private final BinanceProvider provider;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private long lastKlineCloseTime;
    private final String checkpointFile = "last_kline.txt";

    public ContinuousBinanceFetcher(BinanceProvider provider) {
        this.provider = provider;
        this.lastKlineCloseTime = loadLastKline();
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
            provider.fetcher.setStartTime(String.valueOf(lastKlineCloseTime + 1));
            provider.fetcher.setEndTime(String.valueOf(Instant.now().toEpochMilli()));
            BinanceResponse response = provider.provideNews();
            if (!response.candlesticks().isEmpty()) {
                Candlestick last = response.candlesticks().get(response.candlesticks().size() - 1);
                lastKlineCloseTime = last.getKlineCloseTime();
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
            writer.write(String.valueOf(lastKlineCloseTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}