public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Booking createBooking(Booking booking) {
        return bookingService.bookShow(booking);
    }

    public Booking getBooking(Long bookingId) {
        return bookingService.getBookingDetails(bookingId);
    }

    public void cancelBooking(Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}