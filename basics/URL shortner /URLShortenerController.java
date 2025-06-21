public class URLShortenerController {
    private URLShortenerService service;

    public URLShortenerController(URLShortenerService service) {
        this.service = service;
    }

    // Endpoint to shorten a URL (e.g., POST /shorten?longUrl=...)
    public void shortenUrlEndpoint(String longUrl) {
        String shortCode = service.shortenURL(longUrl);
        // In a real app, you would return an HTTP response; here, we print it.
        System.out.println("Short URL: http://short.url/" + shortCode);
    }

    // Endpoint to redirect to the original URL (e.g., GET /{shortCode})
    public void redirectEndpoint(String shortCode) {
        String longUrl = service.getLongUrl(shortCode);
        if (longUrl != null) {
            // In a real app, you would send an HTTP redirect; here, we print the action.
            System.out.println("Redirecting to: " + longUrl);
        } else {
            System.out.println("404 Not Found: " + shortCode);
        }
    }

    // A simple main method to simulate requests
    public static void main(String[] args) {
        URLRepository repository = new InMemoryURLRepository();
        URLShortenerService service = new URLShortenerService(repository);
        URLShortenerController controller = new URLShortenerController(service);

        // Simulate shortening a URL
        controller.shortenUrlEndpoint("https://www.example.com/some/long/url");

        // Assume the generated short code is "b" (or whatever Base62 conversion yields)
        // Simulate a redirection using that short code
        controller.redirectEndpoint("b");
    }
}
