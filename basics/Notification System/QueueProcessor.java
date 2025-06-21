import java.util.LinkedList;
import java.util.Queue;

public class QueueProcessor {
      private Queue<NotificationTask> notificationQueue = new LinkedList<>();
    private RetryManager retryManager = new RetryManager();

    public void addToQueue(String type, String recipient, String message) {
        notificationQueue.add(new NotificationTask(type, recipient, message));
    }

    public void processQueue() {
        while (!notificationQueue.isEmpty()) {
            NotificationTask task = notificationQueue.poll();
            Notification notification = NotificationFactory.createNotification(task.type);
            boolean success = retryManager.executeWithRetry(() -> {
                notification.send(task.recipient, task.message);
                return true;
            });
            if (!success) {
                System.out.println("Failed to send notification to " + task.recipient);
            }
        }
    }
}
