import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// --------------------------------------------
// Message class to represent a message object.
// --------------------------------------------
class Message {
    private final int id;
    private final String content;

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() { 
        return id; 
    }

    public String getContent() { 
        return content; 
    }

    @Override
    public String toString() {
        return "Message{id=" + id + ", content='" + content + "'}";
    }
}

// -------------------------------------------------
// MessageQueue using the Singleton design pattern.
// It wraps a BlockingQueue for thread safety.
// -------------------------------------------------
class MessageQueue {
    private static MessageQueue instance = null;
    private BlockingQueue<Message> queue;

    // Private constructor so it can't be instantiated externally.
    private MessageQueue() {
        queue = new LinkedBlockingQueue<>();
    }

    // Double-checked locking for thread-safe lazy initialization.
    public static MessageQueue getInstance() {
        if (instance == null) {
            synchronized (MessageQueue.class) {
                if (instance == null) {
                    instance = new MessageQueue();
                }
            }
        }
        return instance;
    }

    // Method for producers to add a message.
    public void put(Message message) throws InterruptedException {
        queue.put(message);
    }

    // Method for consumers to retrieve a message.
    public Message take() throws InterruptedException {
        return queue.take();
    }
}

// ------------------------------------------
// Producer class that produces messages.
// ------------------------------------------
class Producer implements Runnable {
    private final int producerId;
    private final int messageCount;

    public Producer(int producerId, int messageCount) {
        this.producerId = producerId;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            // Create a new message (ID is unique per producer).
            Message message = new Message(producerId * 100 + i,
                    "Message from Producer " + producerId + ", message " + i);
            try {
                // Place the message into the queue.
                MessageQueue.getInstance().put(message);
                System.out.println("Producer " + producerId + " produced: " + message);
                // Sleep to simulate work (100ms).
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer " + producerId + " interrupted.");
                break;
            }
        }
    }
}

// ------------------------------------------
// Consumer class that consumes messages.
// ------------------------------------------
class Consumer implements Runnable {
    private final int consumerId;

    public Consumer(int consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        // Continuously take messages from the queue.
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message message = MessageQueue.getInstance().take();
                System.out.println("Consumer " + consumerId + " consumed: " + message);
                // Sleep to simulate processing time (150ms).
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer " + consumerId + " interrupted.");
                break;
            }
        }
    }
}

// ---------------------------------------------------
// Main class to demonstrate the Message Queue System.
// ---------------------------------------------------
public class MessageQueueSystem {
    public static void main(String[] args) {
        // Create two producer threads, each producing 10 messages.
        Thread producer1 = new Thread(new Producer(1, 10));
        Thread producer2 = new Thread(new Producer(2, 10));

        // Create two consumer threads.
        Thread consumer1 = new Thread(new Consumer(1));
        Thread consumer2 = new Thread(new Consumer(2));

        // Start the producer and consumer threads.
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // Wait for producers to finish their work.
        try {
            producer1.join();
            producer2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Allow some time for consumers to process the remaining messages.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop the consumers by interrupting their threads.
        consumer1.interrupt();
        consumer2.interrupt();

        System.out.println("Message Queue System shutdown.");
    }
}
