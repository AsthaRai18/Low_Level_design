public class Location {
    double longitude;
    double latitude;
    public Location(double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public double distance(Location location)
    {
        return Math.sqrt(longitude * longitude + latitude * latitude);
    }
    
}
