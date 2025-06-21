import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessageSystem {
    private List<Message> messages;

    public MessageSystem() {
        this.messages = new ArrayList<>();
    }

    /**
     * Simulates sending a message by storing it.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(Message message) {
        messages.add(message);
    }

    /**
     * Retrieves the conversation between two users sorted by timestamp.
     *
     * @param user1 The first user.
     * @param user2 The second user.
     * @return A list of messages between the two users.
     */
    public List<Message> getConversation(User user1, User user2) {
        List<Message> conversation = new ArrayList<>();
        for (Message msg : messages) {
            if ((msg.getSender().getUserId() == user1.getUserId() && msg.getReceiver().getUserId() == user2.getUserId()) ||
                (msg.getSender().getUserId() == user2.getUserId() && msg.getReceiver().getUserId() == user1.getUserId())) {
                conversation.add(msg);
            }
        }

        // Sort the conversation by timestamp (oldest first)
        Collections.sort(conversation, new Comparator<Message>() {
            @Override
            public int compare(Message m1, Message m2) {
                return m1.getTimestamp().compareTo(m2.getTimestamp());
            }
        });

        return conversation;
    }
}
