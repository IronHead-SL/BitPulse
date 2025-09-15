import infrastructure.adapters.provider.BinanceProvider;
import infrastructure.adapters.provider.ContinuousBinanceFetcher;

public class Main {
    public static void main(String[] args) {
        BinanceProvider provider = new BinanceProvider();
        ContinuousBinanceFetcher fetcher = new ContinuousBinanceFetcher(provider);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down btc fetcher...");
            fetcher.stop();
        }));
        System.out.println("Starting continuous btc data collection from Binance...");
        System.out.println("Press Ctrl+C to stop");
        fetcher.start();
        try {Thread.currentThread().join();}
        catch (InterruptedException e) {System.out.println("Main thread interrupted");}
    }
}
