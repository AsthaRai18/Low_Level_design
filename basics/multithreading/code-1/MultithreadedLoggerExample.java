import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Logger {
    private static Logger instance;
    private final BlockingQueue<String> logQueue;
    private final Thread logWriterThread;
    private volatile boolean running;
    private final String logFilePath = "application.log";

    // Private constructor for Singleton
    private Logger() {
        logQueue = new LinkedBlockingQueue<>();
        running = true;

        // Background thread to write logs to a file
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
            } catch (IOException e){
                System.err.println("Error initializing log file: " + e.getMessage());
                e.printStackTrace();
            }
        });
        logWriterThread.start();
    }

    // Singleton instance getter
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Log message with levels and timestamps
    public void log(String level, String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logMessage = String.format("[%s] [%s] %s", timeStamp, level, message);
        logQueue.offer(logMessage);
    }

    // Log exceptions
    public void logException(String level, String message, Throwable throwable) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(String.format("[%s] [%s] %s - Exception: %s",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                level,
                message,
                throwable.getMessage()));

        for (StackTraceElement element : throwable.getStackTrace()) {
            logMessage.append(System.lineSeparator()).append("\tat ").append(element);
        }

        logQueue.offer(logMessage.toString());
    }

    // Graceful shutdown
    public void shutdown() {
        running = false;
        try {
            logWriterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Logger shutdown interrupted.");
        }
    }
}

// Test class with multithreading and error handling
public class MultithreadedLoggerExample {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        Runnable task = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    if (i == 5) throw new RuntimeException("Simulated Exception at " + i);
                    logger.log("INFO", Thread.currentThread().getName() + " - Log message " + i);
                }
            } catch (Exception e) {
                logger.logException("ERROR", Thread.currentThread().getName() + " encountered an error", e);
            }
        };

        // Start multiple threads to log messages
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to complete
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.logException("ERROR", "Main thread interrupted", e);
        }

        // Shutdown the logger
        logger.shutdown();
        System.out.println("Logging completed. Check the application.log file.");
    }
}
