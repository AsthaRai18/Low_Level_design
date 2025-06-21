import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface URLRepository {
    void save(URLMapping mapping);
    URLMapping findByShortUrl(String shortUrl);
}

// An in-memory implementation (for demonstration)
class InMemoryURLRepository implements URLRepository {
    private Map<String, URLMapping> storage = new ConcurrentHashMap<>();

    @Override
    public void save(URLMapping mapping) {
        storage.put(mapping.getShortUrl(), mapping);
    }

    @Override
    public URLMapping findByShortUrl(String shortUrl) {
        return storage.get(shortUrl);
    }
}
