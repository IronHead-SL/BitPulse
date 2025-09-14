import infrastructure.adapters.BinanceProvider;
import infrastructure.adapters.ContinuousBinanceFetcher;

public class Main {
    public static void main(String[] args) {
        BinanceProvider provider = new BinanceProvider();
        ContinuousBinanceFetcher fetcher = new ContinuousBinanceFetcher(provider);
        fetcher.start();
    }
}
