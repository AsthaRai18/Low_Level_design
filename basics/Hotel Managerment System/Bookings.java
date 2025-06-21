public class Bookings {
    private Guests guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    
    public Bookings(Guests guest, Room room, String checkInDate, String checkOutDate) {
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

}
