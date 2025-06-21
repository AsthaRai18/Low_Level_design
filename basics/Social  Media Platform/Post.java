import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private int postId;
    private User author;
    private String content;
    private Date timestamp;
    private List<Interaction> interactions;

    public Post(int postId, User author, String content, Date timestamp) {
        this.postId = postId;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.interactions = new ArrayList<>();
    }

    // Getters
    public int getPostId() {
        return postId;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    // Methods
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
}
