import java.util.Date;

public class Interaction {
    private User user;
    private String interactionType; 
    private Date timestamp;
    private String comment; // Optional; may be null if not a comment

    public Interaction(User user, String interactionType, Date timestamp, String comment) {
        this.user = user;
        this.interactionType = interactionType;
        this.timestamp = timestamp;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getComment() {
        return comment;
    }
}
