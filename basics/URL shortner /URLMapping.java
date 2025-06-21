import java.util.Date;

public class URLMapping {
    private long id;         // Unique auto-incremented ID
    private String longUrl;  // Original long URL
    private String shortUrl; // Generated short code (e.g., "aZ12")
    private Date createdAt;  // Timestamp when mapping was created

    public URLMapping(long id, String longUrl, String shortUrl, Date createdAt) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
    }

    // Getters (and setters if needed)
    public long getId() { return id; }
    public String getLongUrl() { return longUrl; }
    public String getShortUrl() { return shortUrl; }
    public Date getCreatedAt() { return createdAt; }
}
