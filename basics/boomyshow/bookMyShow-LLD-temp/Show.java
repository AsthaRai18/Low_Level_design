import java.util.concurrent.ConcurrentHashMap;

public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private Map<SeatType,Double> seatTypePriceMap;
    private Map<Seat,SeatStatus> seats = new ConcurrentHashMap<>();
    public synchronized boolean reserveSeats()
    {
        
    }
}
