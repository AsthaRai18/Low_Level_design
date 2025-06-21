import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Bucket {
    private int tokens;
    private final int capacity;
    private final int refillRatePerSecond;

    public Bucket(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
    }

    // Synchronized to ensure thread safety
    public synchronized void refill() {
        tokens = Math.min(capacity, tokens + refillRatePerSecond);
        System.out.println("Refilled. Tokens now: " + tokens);
    }

    public synchronized boolean requestParse(String req) {
        if (tokens > 0) {
            tokens--;
            System.out.println("Request allowed: " + req);
            return true;
        } else {
            System.out.println("Request denied: " + req);
            return false;
        }
    }
}

public class TokenBucket {
    public static void main(String[] args) {
        Bucket bucket = new Bucket(5, 1); // capacity 5, refill 1 token/sec

        // Background token refill using scheduled executor
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(bucket::refill, 0, 1, TimeUnit.SECONDS);

        // Simulate incoming requests
        Runnable requestTask = () -> {
            for (int i = 0; i < 10; i++) {
                bucket.requestParse("Request " + i);
                try {
                    Thread.sleep(300); // Simulate 300ms between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(requestTask).start();
    }
}
