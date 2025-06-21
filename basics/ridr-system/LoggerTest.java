import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

class Logger {
    //this is for thread safety they say
    private static volatile Logger loggerinstance;
    private static final String LOG_FILE = "app.log";
    public synchronized void log(Logsystem level, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logMessage = timestamp + " [" + level + "] " + message;

        // Print to console
        System.out.println(logMessage);

        // Write to file
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write to log file.");
        }
    }
    public void info(String message) {
        log(Logsystem.INFO, message);
    }

    public void warning(String message) {
        log(Logsystem.WARNING, message);
    }

    public void error(String message) {
        log(Logsystem.ERROR, message);
    }

    private Logger() {
    }
    public static Logger getInstance()
    {
        if(loggerinstance == null)
        {
            synchronized(Logger.class)
            {
                if(loggerinstance == null)
                {
                    loggerinstance = new Logger();
                }
            }
        }
        return loggerinstance;
    }

}
public class LoggerTest
{   
    public static void main(String args[])
    {
        Logger logger = Logger.getInstance();
        logger.log("Hello World");
    }
}