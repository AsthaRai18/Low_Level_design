import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Topic {
    String name;
    Queue<String> messages = new LinkedList<>();
    Set<String> subscribers = new HashSet<>();
    Lock lock = new ReentrantLock();

    public Topic(String name) {
        this.name = name;
    }

    public void addSubscriber(String consumerName) {
        subscribers.add(consumerName);
    }

    public void addMessage(String message) {
        lock.lock();
        try {
            messages.offer(message);
        } finally {
            lock.unlock();
        }
    }

    public String getMessage() {
        lock.lock();
        try {
            return messages.poll();
        } finally {
            lock.unlock();
        }
    }

    public boolean hasMessages() {
        lock.lock();
        try {
            return !messages.isEmpty();
        } finally {
            lock.unlock();
        }
    }
}

class MessageBroker {
    Map<String, Topic> topics = new ConcurrentHashMap<>();
    Map<String, Consumer> consumers = new ConcurrentHashMap<>();

    public void createTopic(String topicName) {
        topics.put(topicName, new Topic(topicName));
    }

    public void createConsumer(String consumerName) {
        consumers.put(consumerName, new Consumer(consumerName, this));
    }

    public void subscribe(String consumerName, String topicName) {
        if (topics.containsKey(topicName)) {
            topics.get(topicName).addSubscriber(consumerName);
        }
    }

    public void publish(String topicName, String message) {
        if (topics.containsKey(topicName)) {
            topics.get(topicName).addMessage(message);
        }
    }

    public Topic getTopic(String topicName) {
        return topics.get(topicName);
    }
}

class Producer extends Thread {
    private MessageBroker broker;
    private String producerName;

    public Producer(String name, MessageBroker broker) {
        this.producerName = name;
        this.broker = broker;
    }

    public void run() {
        try {
            if (producerName.equals("producer1")) {
                broker.publish("topic1", "Message 1");
                Thread.sleep(100);
                broker.publish("topic1", "Message 2");
                Thread.sleep(100);
                broker.publish("topic2", "Message 4");
            } else if (producerName.equals("producer2")) {
                broker.publish("topic1", "Message 3");
                Thread.sleep(100);
                broker.publish("topic2", "Message 5");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    private String name;
    private MessageBroker broker;
    private List<String> subscribedTopics = new ArrayList<>();

    public Consumer(String name, MessageBroker broker) {
        this.name = name;
        this.broker = broker;
    }

    public void subscribe(String topicName) {
        subscribedTopics.add(topicName);
    }

    public void run() {
        while (true) {
            boolean foundMessage = false;
            for (String topicName : subscribedTopics) {
                Topic topic = broker.getTopic(topicName);
                if (topic != null && topic.hasMessages()) {
                    String message = topic.getMessage();
                    if (message != null) {
                        System.out.println("[" + name + "] received from [" + topicName + "]: " + message);
                        foundMessage = true;
                    }
                }
            }
            if (!foundMessage) {
                try {
                    Thread.sleep(100); // Wait before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class DistributedQueueSystem {
    public static void main(String[] args) throws InterruptedException {
        MessageBroker broker = new MessageBroker();

        // Create topics
        broker.createTopic("topic1");
        broker.createTopic("topic2");

        // Create consumers
        for (int i = 1; i <= 5; i++) {
            broker.createConsumer("consumer" + i);
        }

        // Subscribe consumers
        for (int i = 1; i <= 5; i++) {
            broker.subscribe("consumer" + i, "topic1");
            broker.consumers.get("consumer" + i).subscribe("topic1");
        }
        broker.subscribe("consumer1", "topic2");
        broker.subscribe("consumer3", "topic2");
        broker.subscribe("consumer4", "topic2");

        broker.consumers.get("consumer1").subscribe("topic2");
        broker.consumers.get("consumer3").subscribe("topic2");
        broker.consumers.get("consumer4").subscribe("topic2");

        // Start consumer threads
        for (Consumer consumer : broker.consumers.values()) {
            consumer.start();
        }

        // Create producers
        Producer producer1 = new Producer("producer1", broker);
        Producer producer2 = new Producer("producer2", broker);

        // Start producer threads
        producer1.start();
        producer2.start();

        // Let the system run for some time
        Thread.sleep(5000);

        // Kill the app (for clean shutdown you can interrupt threads, skipped here)
        System.exit(0);
    }
}
