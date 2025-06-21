package BookMyshow;
import java.util.*;
class Seat{
    Integer no;
    Boolean isOccupied;    
}
// 1. Functional Requirements
//     * user should be able to search a event based on some title, location, date
//     * user should be able to view details of events (seat, description, metadata)
//     * user should be able to do booking for that event
// 2. Non Functional Requirements
//     * Scale: 100M DAU
//     * CAP Theorem: Highly availbale wrt searching and viewing event and highly consistent wrt to booking a particular ticket
// 3. Core Entity:
// * User
// * Event
// * Venue
// * Ticket
// 1. API Designing
// GET: /v1/search?q={searchTerm}&location={Location}&date={Date} -> List<EventID>: Pagination
// GET: /v1/event/{EventID} -> Event Details & Location & Seats[]
// POST:  /v1/booking/reserve
// 		{
// 			List<Seat> seats;
// 		}

// 		/v1/booking/confirm
// 		{
// 			bookingID,
// 			paymentDetails
// 		}
package BookMyshow;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class User {
    String userId;
    String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

class Seat {
    String seatId;
    int number;
    boolean isOccupied;

    public Seat(String seatId, int number) {
        this.seatId = seatId;
        this.number = number;
        this.isOccupied = false;
    }
}

class Venue {
    String venueId;
    String name;
    List<Seat> seats = new ArrayList<>();

    public Venue(String venueId, String name, int seatCount) {
        this.venueId = venueId;
        this.name = name;
        for (int i = 1; i <= seatCount; i++) {
            seats.add(new Seat("S" + i, i));
        }
    }
}

class Event {
    String eventId;
    String title;
    String location;
    String date;
    String description;
    Venue venue;

    public Event(String eventId, String title, String location, String date, String desc, Venue venue) {
        this.eventId = eventId;
        this.title = title;
        this.location = location;
        this.date = date;
        this.description = desc;
        this.venue = venue;
    }
}

enum BookingStatus {
    PENDING, CONFIRMED
}

class Booking {
    String bookingId;
    String userId;
    String eventId;
    List<String> seatIds;
    BookingStatus status;

    public Booking(String bookingId, String userId, String eventId, List<String> seatIds) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.seatIds = seatIds;
        this.status = BookingStatus.PENDING;
    }
}

class BookingService {
    Map<String, Event> events = new HashMap<>();
    Map<String, Booking> bookings = new ConcurrentHashMap<>();
    Map<String, Object> seatLocks = new ConcurrentHashMap<>();

    public BookingService() {
        Venue venue = new Venue("V1", "Stadium 1", 10);
        Event event = new Event("E1", "Rock Concert", "NYC", "2025-04-12", "Live music!", venue);
        events.put(event.eventId, event);

        // create locks for each seat
        for (Seat seat : venue.seats) {
            seatLocks.put(seat.seatId, new Object());
        }
    }

    public List<Event> searchEvent(String title, String location, String date) {
        List<Event> result = new ArrayList<>();
        for (Event e : events.values()) {
            if ((title == null || e.title.contains(title)) &&
                (location == null || e.location.equals(location)) &&
                (date == null || e.date.equals(date))) {
                result.add(e);
            }
        }
        return result;
    }

    public Event getEventDetails(String eventId) {
        return events.get(eventId);
    }

    public String reserveSeats(String userId, String eventId, List<String> seatIds) {
        Event event = events.get(eventId);
        if (event == null) return null;

        synchronized (this) {
            for (String seatId : seatIds) {
                Seat seat = event.venue.seats.stream()
                        .filter(s -> s.seatId.equals(seatId))
                        .findFirst().orElse(null);
                if (seat == null || seat.isOccupied) {
                    throw new RuntimeException("Seat " + seatId + " is already occupied or not found.");
                }
            }

            for (String seatId : seatIds) {
                synchronized (seatLocks.get(seatId)) {
                    Seat seat = event.venue.seats.stream().filter(s -> s.seatId.equals(seatId)).findFirst().get();
                    seat.isOccupied = true;
                }
            }

            String bookingId = UUID.randomUUID().toString();
            Booking booking = new Booking(bookingId, userId, eventId, seatIds);
            bookings.put(bookingId, booking);
            return bookingId;
        }
    }

    public boolean confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) return false;
        booking.status = BookingStatus.CONFIRMED;
        return true;
    }

    public void printSeats(String eventId) {
        Event e = events.get(eventId);
        System.out.println("Seats for Event: " + e.title);
        for (Seat seat : e.venue.seats) {
            System.out.println("Seat " + seat.seatId + " - " + (seat.isOccupied ? "Occupied" : "Available"));
        }
    }
}

public class bookmyshow {
    public static void main(String[] args) {
        BookingService service = new BookingService();
        User user = new User("U1", "Alice");

        System.out.println("Searching for events...");
        List<Event> events = service.searchEvent("Rock", "NYC", "2025-04-12");
        for (Event e : events) {
            System.out.println(e.eventId + ": " + e.title + " at " + e.location);
        }

        String eventId = events.get(0).eventId;
        service.printSeats(eventId);

        System.out.println("\nReserving seats S1 and S2...");
        String bookingId = service.reserveSeats(user.userId, eventId, Arrays.asList("S1", "S2"));
        System.out.println("Booking ID: " + bookingId);

        System.out.println("\nConfirming booking...");
        boolean confirmed = service.confirmBooking(bookingId);
        System.out.println("Confirmed: " + confirmed);

        service.printSeats(eventId);
    }
}
