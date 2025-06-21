public class UserPreferenceManager {
    public void updatePreferences(String type, String recipient, String message) {
        NotificationTask notificationTask = new NotificationTask(type, recipient, message);
        Notification notification = NotificationFactory.getNotification(notificationTask);
        notification.prepare(notificationTask.getData());
        notification.send();
    }
}
