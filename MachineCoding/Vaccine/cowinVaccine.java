package Vaccine;
// Register a User
// Search the Vaccine center near by
// Book slots for particular center
// update the information of the booking (means we can delete or postponed)
// send the notification
import java.util.*;
class user{
    String id;
    String name;
    Long phoneNumber;
}
class Location{
    String pincode;
    String longitude;
    String latitude;
}
enum slot
{
    a,b,c
}
class center
{
    String center_id;
    String name;
    Map<String,Integer>slots;
    public center()
    {
        slots = new HashMap<>();
        
    }
    public void addSlots(String name, Integer i)
    {
        slots.put(name,i);
    }
}
class Booking
{   
    Integer id;
    center center;
    slot slot;
}
class BookingService{
    Map<Integer,user>userMap;


}
public class cowinVaccine {
    
}
