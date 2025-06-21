import java.util.List;

public class BookingService {

    public boolean makeBooking(User user, Screen screen, List<Seat> seats) {
        //fetch all the seats which are not reserved
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setScreen(screen);
        booking.setBookingTime(new Date());
        booking.setBookingDate(new Date());
        booking.setBookingStatus("BOOKED");
        booking.setPaymentStatus("PAID");
        for(auto it: seats) {
            it.setIsReserved(true);
        }
        booking.setSeats(seats);
        return true;
    }

    public boolean cancelBooking(Booking booking) {
        List<Seat> seats = booking.getSeats();
        for(auto it: seats) {
            it.setIsReserved(false);
        }
        booking.setBookingStatus("CANCELLED");
        return true;
    }

    public boolean blockSeats(Screen screen, List<Seat> seats) {
        
        return true;
    }

    public boolean unblockSeats(Screen screen, List<Seat> seats) {
        // Implementation here
        return true;
    }
}
