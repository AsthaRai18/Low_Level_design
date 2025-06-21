public class Rider extends User
{
    public Rider(Long id, String name, String phoneNumber)
    {
        super(id, name, phoneNumber);
    }
    public Ride Riderequest(Location from, Location to)
    {
        return new Ride( id, from, to);
    }
}
