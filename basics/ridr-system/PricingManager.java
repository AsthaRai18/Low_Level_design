public class PricingManager {
    private static final double MINIMUM_FARE = 5.0;
    private static final double PER_KM_FARE = 2.0;
    public double calculateFare(Location source, Location destination)
    {
        double distance = source.distance(destination);
        double fare = distance * PER_KM_FARE;
        return Math.max(fare, MINIMUM_FARE);
    }
}
