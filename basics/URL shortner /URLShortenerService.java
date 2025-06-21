import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class URLShortenerService {
    private URLRepository repository;
    // AtomicLong to simulate auto-increment (thread-safe)
    private AtomicLong counter = new AtomicLong(1);

    public URLShortenerService(URLRepository repository) {
        this.repository = repository;
    }

    // Shortens the long URL
    public String shortenURL(String longUrl) {
        // Generate a unique ID for this URL
        long id = counter.getAndIncrement();
        // Encode the ID to a Base62 string
        String shortCode = Base62Encoder.encode(id);
        // Create a new URL mapping and save it
        URLMapping mapping = new URLMapping(id, longUrl, shortCode, new Date());
        repository.save(mapping);
        return shortCode;
    }

    // Retrieves the long URL from a short code
    public String getLongUrl(String shortUrl) {
        URLMapping mapping = repository.findByShortUrl(shortUrl);
        return mapping != null ? mapping.getLongUrl() : null;
    }
}
