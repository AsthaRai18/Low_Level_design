import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
 
public class ExpiringKVStore {

    // Internal class to hold value and expiration time.
    private static class Entry {
        String value;
        long expirationTime; // in milliseconds since epoch

        Entry(String value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
    }

    // Internal class to represent an entry in the expiration queue.
    private static class ExpireEntry implements Comparable<ExpireEntry> {
        String key;
        long expirationTime;

        ExpireEntry(String key, long expirationTime) {
            this.key = key;
            this.expirationTime = expirationTime;
        }

        @Override
        public int compareTo(ExpireEntry other) {
            return Long.compare(this.expirationTime, other.expirationTime);
        }
    }

    // The key-value store.
    private final Map<String, Entry> store = new HashMap<>();
    // Min-heap to keep track of key expirations.
    private final PriorityQueue<ExpireEntry> expirationQueue = new PriorityQueue<>();
    private final Queue<ExpireEntry>expirationQueue1 = new LinkedList<>();

    // Lock for synchronizing access.
    private final Object lock = new Object();

    // Cleanup thread control.
    private boolean stopCleanup = false;
    private final Thread cleanupThread;

    public ExpiringKVStore() {
        // Start the background cleanup thread.
        cleanupThread = new Thread(this::cleanupTask);
        cleanupThread.start();
    }

    /**
     * Set a key with its value and TTL.
     * @param key Key to store.
     * @param value Associated value.
     * @param ttlSeconds Time to live in seconds; if 0 or negative, the key will never expire.
     */
    public void set(String key, String value, int ttlSeconds) {
        long expirationTime = (ttlSeconds > 0)
                ? System.currentTimeMillis() + ttlSeconds * 1000L
                : Long.MAX_VALUE;

        synchronized (lock) {
            store.put(key, new Entry(value, expirationTime));
            if (ttlSeconds > 0) {
                expirationQueue.offer(new ExpireEntry(key, expirationTime));
                // Notify cleanup thread in case this key expires sooner.
                lock.notifyAll();
            }
        }
    }

    /**
     * Get the value associated with the key.
     * @param key The key to retrieve.
     * @return The value, or null if not found or expired.
     */
    public String get(String key) {
        synchronized (lock) {
            Entry entry = store.get(key);
            if (entry == null) {
                return null;
            }
            // Check if the key has expired.
            if (System.currentTimeMillis() >= entry.expirationTime) {
                store.remove(key);
                return null;
            }
            return entry.value;
        }
    }

    /**
     * Delete a key from the store.
     * @param key The key to delete.
     */
    public void delete(String key) {
        synchronized (lock) {
            store.remove(key);
            // Note: We do not remove it from the expirationQueue here.
            // The cleanup thread will skip missing keys.
        }
    }

    /**
     * Shutdown the store and stop the cleanup thread.
     */
    public void shutdown() {
        synchronized (lock) {
            stopCleanup = true;
            lock.notifyAll();
        }
        try {
            cleanupThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // The background cleanup task that removes expired keys.
    private void cleanupTask() {
        while (true) {
            synchronized (lock) {
                if (stopCleanup) {
                    break;
                }
                long now = System.currentTimeMillis();

                // If the queue is empty, wait indefinitely until notified.
                if (expirationQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    continue;
                }

                ExpireEntry expireEntry = expirationQueue.peek();
                if (expireEntry.expirationTime <= now) {
                    // Remove the entry from the queue.
                    expirationQueue.poll();
                    // Double-check: if the key exists and is expired, remove it.
                    Entry entry = store.get(expireEntry.key);
                    if (entry != null && entry.expirationTime <= now) {
                        store.remove(expireEntry.key);
                    }
                } else {
                    // Wait until the next key is due to expire.
                    long waitTime = expireEntry.expirationTime - now;
                    try {
                        lock.wait(waitTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    // Simple test harness.
    public static void main(String[] args) throws InterruptedException {
        ExpiringKVStore kvStore = new ExpiringKVStore();
        kvStore.set("key1", "value1", 3); // expires in 3 seconds
        kvStore.set("key2", "value2", 0); // no expiration

        System.out.println("key1: " + kvStore.get("key1")); // should print "value1"
        System.out.println("key2: " + kvStore.get("key2")); // should print "value2"

        // Wait for key1 to expire.
        Thread.sleep(4000);
        System.out.println("After expiration:");
        System.out.println("key1: " + kvStore.get("key1")); // should print null
        System.out.println("key2: " + kvStore.get("key2")); // should still print "value2"

        // Shutdown the store (stops the cleanup thread).
        kvStore.shutdown();
    }
}
