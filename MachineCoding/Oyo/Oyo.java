package Oyo;
import java.util.*;
class Hotel
{
    private int hotelId;
    private String hotelName;
    private List<Room>rooms;
    public List<Room> getRooms()
    {
        return rooms;
    }
}
class Room
{
    private int RoomId;
    private Hotel hotel;
    private boolean isOccupied;
    private Double price;
}
enum BookingStatus
{
    PENDING, CANCELLED , CONFIRMED
}
enum PaymentStatus
{
    PENDING , CANCELLED, CONFIRMED
}
class Booking
{
    private int BookingId;
    private BookingStatus bookingStatus;
    private User user;
}
class User
{
    private int userId;
}
class OyoService
{
    List<Hotel>hotelsList;
    public List<Hotel>getHotels()
    {
        return hotelsList;
    }
    public List<Room>getRooms(Hotel hotel)
    {
        return hotel.getRooms();
    }
    public void bookRoom(Hotel hotel, Room room,User user)
    {
        Booking booking = new Booking();
    
    }
}
public class Oyo {
    
}
