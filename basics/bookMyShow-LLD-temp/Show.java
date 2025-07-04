import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private Map<SeatType,Double> seatTypePriceMap;
    private Map<Seat,SeatStatus> seats = new ConcurrentHashMap<>();
    // public synchronized boolean reserveSeats()
    // {
        
    // }
}
