import java.util.List;

public class Room
{
    private int roomNumber;
    private List<Bookings> bookings;
    public Room(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }
    public void addBooking(Bookings booking)
    {
        bookings.add(booking);
    }
}