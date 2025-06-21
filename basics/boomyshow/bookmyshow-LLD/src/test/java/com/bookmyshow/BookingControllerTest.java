import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bookmyshow.controllers.BookingController;
import com.bookmyshow.models.Booking;
import com.bookmyshow.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBooking() throws Exception {
        Booking booking = new Booking();
        booking.setBookingId(1);
        // Set other properties of booking as needed

        when(bookingService.bookShow(any())).thenReturn(booking);

        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ /* JSON representation of booking */ }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(1));
    }

    @Test
    public void testGetBooking() throws Exception {
        Booking booking = new Booking();
        booking.setBookingId(1);
        // Set other properties of booking as needed

        when(bookingService.getBookingDetails(1)).thenReturn(booking);

        mockMvc.perform(get("/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(1));
    }

    @Test
    public void testCancelBooking() throws Exception {
        doNothing().when(bookingService).cancelBooking(1);

        mockMvc.perform(delete("/bookings/1"))
                .andExpect(status().isNoContent());
    }
}