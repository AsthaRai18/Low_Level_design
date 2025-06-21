public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking bookShow(User user, Show show, List<String> seatNumbers) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setSeatNumbers(seatNumbers);
        // Additional logic for booking can be added here
        return bookingRepository.save(booking);
    }

    public Booking getBookingDetails(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}