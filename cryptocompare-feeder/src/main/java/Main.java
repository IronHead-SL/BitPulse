import infrastructure.adapters.ContinuousNewsFetcher;
import infrastructure.adapters.CryptoCompareProvider;

public class Main {
    public static void main(String[] args) {
        CryptoCompareProvider provider = new CryptoCompareProvider(System.getenv("CRYPTOCOMPARE_API_KEY"));
        ContinuousNewsFetcher fetcher = new ContinuousNewsFetcher(provider);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down news fetcher...");
            fetcher.stop();
        }));
        System.out.println("Starting continuous news collection from CryptoCompare...");
        System.out.println("Press Ctrl+C to stop");
        fetcher.start();
        try {Thread.currentThread().join();}
        catch (InterruptedException e) {System.out.println("Main thread interrupted");}
    }
}
