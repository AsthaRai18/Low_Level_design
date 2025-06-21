public class User{
    private int userId;
    private String username;
    private String email;
    private Profile profile;

    public User(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profile = new Profile();
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Profile getProfile() {
        return profile;
    }

    // Methods
    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }
}