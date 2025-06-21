import java.util.HashMap;
import java.util.Map;

public class Rating {
    private Map<User, Double> userRatings = new HashMap<>();
    public void rateUser(User user, double rating) {
        userRatings.put(user, rating);
    }
}
