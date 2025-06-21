public class Trip {
    private long tripId;
    private long riderId;
    private Location from;
    private Location to;
    private double fare;
    private PricingManager pricingManager;

    public Trip(long riderId, Location from, Location to, PricingManager pricingManager) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Locations cannot be null");
        }
        this.tripId = IdGenerator.generateRideId();
        this.riderId = riderId;
        this.from = from;
        this.to = to;
        this.pricingManager = pricingManager;
        this.fare = pricingManager.calculateFare(from, to);
    }

    public long getTripId() {
        return tripId;
    }

    public long getRiderId() {
        return riderId;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public double getFare() {
        return fare;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", riderId=" + riderId +
                ", from=" + from +
                ", to=" + to +
                ", fare=" + fare +
                '}';
    }

    public static boolean processPayment(Rider rider, double amount) {
        System.out.println("Processing payment of $" + amount + " for " + rider.name);
        return true;
    }
}
