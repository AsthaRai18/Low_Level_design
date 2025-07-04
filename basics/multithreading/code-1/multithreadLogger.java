import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Logger {
    private static Logger instance;
    private final BlockingQueue<String> logQueue;
    private final Thread logWriterThread;
    private volatile boolean running;
    private final String logFilePath = "application.log";

    private Logger() {
        logQueue = new LinkedBlockingQueue<>();
        running = true;

        logWriterThread = new Thread(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
                while (running || !logQueue.isEmpty()) {
                    String logMessage = logQueue.poll();
                    if (logMessage != null) {
                        try {
                            writer.write(logMessage);
                            writer.newLine();
                        } catch (IOException e) {
                            System.err.println("Error writing log message: " + logMessage);
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error initializing log file: " + e.getMessage());
                e.printStackTrace();
            }
        });
        logWriterThread.start();
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logQueue.offer(message);
    }

    public void shutdown() {
        running = false;
        try {
            logWriterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class multithreadLogger {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Log message 1");
        logger.log("Log message 2");
        logger.log("Log message 3");

        logger.shutdown();
    }
}
