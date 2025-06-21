import java.io.BufferedWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

class Logger
{
    private static Logger instance;
    private static final String logFilePath = "application.log";
    private volatile boolean running;
    private final Thread logThread2;
    BlockingQueue<String> logQueue;
    Thread logThread;
    public static synchronized Logger getInstance()
    {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
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
                } catch (IOException e) {
                    System.err.println("Error initializing log file: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            logWriterThread.start();
        }
    
}
public class multithreadLogger {
    public static void main(String args[])
    {

    }
}
