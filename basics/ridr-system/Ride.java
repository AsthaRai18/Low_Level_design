public class Ride {
    long RideId;
    long driverId;
    long riderId;
    Location from;
    Location to;
    public Ride(long riderId, Location from, Location to)
    {
        RideId = IdGenerator.generateRideId();
        this.riderId = riderId;
        this.from = from;
        this.to = to;
    }
   
}

