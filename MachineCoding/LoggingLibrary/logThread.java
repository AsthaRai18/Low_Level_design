import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
enum LogLevel
{
    INFO,DEBUG,ERROR
}
class Logger{
    private final BlockingQueue<String> logQueue;
    private final Thread logThread;
    private volatile boolean running;
    public Logger()
    {
        logQueue = new LinkedBlockingQueue<>();
        running = true;
        logThread = new Thread(() -> {
            while (running || !logQueue.isEmpty()) {
                try {
                    // Take a log message from the queue and log it
                    String message = logQueue.take();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        logThread.start();
    }
    public void log(String msg)
    {
        if(running)
        {
            try
            {
            logQueue.put(msg);
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    }
    public void stop()
    {
        running = false;
        logThread.interrupt();
    }
}
public class logThread {
    public static void main(String args[])
    {
        Logger logger = new Logger();
        Thread thread1 = new Thread(()->{
            for(int i = 0;i<10;i++)
            {
                logger.log("THread 1 - Message "+i);
            }
        });

    Thread thread2 = new Thread(() -> {
        for (int i = 0; i < 5; i++) {
            logger.log("Thread 2 - Message " + i);
        }
    });

    Thread thread3 = new Thread(() -> {
        for (int i = 0; i < 5; i++) {
            logger.log("Thread 3 - Message " + i);
        }
    });
    thread1.start();
    thread2.start();
    thread3.start();

}
}