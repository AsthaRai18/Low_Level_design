import java.util.HashSet;
import java.util.Set;

public class Profile {
    private String bio;
    private String profilePicture;
    private String coverPhoto;
    private Set<Integer> followers;
    private Set<Integer> following;

    public Profile() {
        this.bio = "";
        this.profilePicture = null;
        this.coverPhoto = null;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    // Getters
    public String getBio() {
        return bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public Set<Integer> getFollowers() {
        return followers;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    // Methods
    public void addFollower(int userId) {
        followers.add(userId);
    }

    public void follow(int userId) {
        following.add(userId);
    }

    public void updateBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}
