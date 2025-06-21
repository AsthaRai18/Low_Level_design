/**
 * A simple token bucket rate limiter.
 */
public class TokenBucketRateLimiter {
    private final int capacity;           // Maximum number of tokens in the bucket.
    private final int refillRate;         // Number of tokens added per second.
    private double tokens;                // Current number of available tokens.
    private long lastRefillTimestamp;     // Last time the bucket was refilled (in nanoseconds).

    /**
     * @param capacity  Maximum tokens the bucket can hold.
     * @param refillRate Tokens added per second.
     */
    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;                      // Start with a full bucket.
        this.lastRefillTimestamp = System.nanoTime();  // Record the current time.
    }

    /**
     * Attempts to consume one token.
     * @return true if a token was available and consumed (i.e. request allowed), otherwise false.
     */
    public synchronized boolean tryConsume() {
        refill(); // Update token count based on elapsed time.
        if (tokens >= 1) {
            tokens -= 1; // Consume one token.
            return true;
        }
        return false;
    }

    /**
     * Refills tokens based on the time elapsed since the last refill.
     */
    private void refill() {
        long now = System.nanoTime();
        double secondsElapsed = (now - lastRefillTimestamp) / 1e9;
        double tokensToAdd = secondsElapsed * refillRate;

        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }

    // --- Testing the Rate Limiter ---
    public static void main(String[] args) {
        // Create a rate limiter with a bucket capacity of 5 tokens and a refill rate of 2 tokens per second.
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(5, 2);

        // Simulate 20 requests, pausing 300 milliseconds between each.
        for (int i = 1; i <= 20; i++) {
            if (rateLimiter.tryConsume()) {
                System.out.println("Request " + i + " allowed");
            } else {
                System.out.println("Request " + i + " rejected due to rate limiting");
            }

            // Pause between requests.
            try {
                Thread.sleep(300); // 300 ms pause
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
