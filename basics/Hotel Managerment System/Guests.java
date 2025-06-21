import java.util.List;

public class Guests {
    private long id;
    private String name;
    private String email;
    private List<Bookings> bookings;
    public Guests(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
