import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class searchIndex {
    private Map<String,List<Message>> index = new HashMap<>();
   public void addMessage(Message message) {
        String[] words = message.getContent().toLowerCase().split("\\W+");
        for (String word : words) {
            if (!word.isEmpty()) {
                index.computeIfAbsent(word, k -> new ArrayList<>()).add(message);
            }
        }
    }
    public List<Message> search(String query) {
        return index.getOrDefault(query.toLowerCase(), Collections.emptyList());
    }
}
