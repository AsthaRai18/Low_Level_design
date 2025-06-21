import java.util.*;

public interface Notification {
    void send();
    void prepare(Map<String, Object> data);
}

