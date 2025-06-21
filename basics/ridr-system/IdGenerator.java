import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong rideIdCounter = new AtomicLong(1000);
    public static long generateRideId() {
        return rideIdCounter.incrementAndGet();
    }
}
