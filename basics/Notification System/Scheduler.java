public class Scheduler {
    private QueueProcessor queueProcessor;
    public Scheduler(QueueProcessor queueProcessor) {
        this.queueProcessor = queueProcessor;
    }
    public void schedule(String type, String recipient, String message) {
        queueProcessor.addToQueue(type, recipient, message);
    }
}
