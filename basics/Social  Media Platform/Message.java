import java.util.Date;

public class Message {
    private User sender;
    private User receiver;
    private String content;
    private Date timestamp;

    public Message(User sender, User receiver, String content, Date timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters
    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
