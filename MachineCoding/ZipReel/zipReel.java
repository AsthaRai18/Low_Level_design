package ZipReel;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
class Movie
{
    String title;
    String movieId;
    String genre;
    int releaseYear;
    int rating;
    public String getMovieId()
    {
        return movieId;
    }
    public String getTitle()
    {
        return title;
    }
}
class User
{
    String userId;
    String name;
    String preferredGenre;
}
class PrimaryStore
{
    private Map<String,Movie>movieMap = new HashMap<>();
    public void addMovie(Movie movie)
    {
        movieMap.put(movie.getMovieId(),movie);
    }
    public List<Movie>searchMovies(String title, String genre, int releaseYear)
    {
        return movieMap.values().stream().filter(movie->(title==null || movie.getTitle().equalsIgnoreCase(title))).collect(Collectors.toList());
    }
}
class L1Cache
{
    
}
public class zipReel {
    
}
