public class Driver extends User{
    boolean isAvailable;
    public Driver(Long id, String name, String phoneNumber)
    {
        super(id, name, phoneNumber);
    }
    public Location getDriverLocation()
    {
        return new Location(0, 0);
    }
    public void setAvailability(boolean isAvailable)
    {
        this.isAvailable = isAvailable;
    }
    public boolean isAvailable()
    {
        return isAvailable;
    }
}
