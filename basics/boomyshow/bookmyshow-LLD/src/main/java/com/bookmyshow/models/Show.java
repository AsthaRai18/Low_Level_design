public class Show {
    private String showId;
    private Movie movie;
    private String showTime;
    private int availableSeats;

    public Show(String showId, Movie movie, String showTime, int availableSeats) {
        this.showId = showId;
        this.movie = movie;
        this.showTime = showTime;
        this.availableSeats = availableSeats;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}