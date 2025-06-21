class Log{

    private static Log instance = null;
    public static Log getInstance()
    {
        if(instance == null)
        {
            synchronized(Log.class)
            {
                if(instance == null)
                {
                    instance = new Log();
                }
            }
        }
        return instance;
    }
    public void log(String message, LogLevel logLevel)
    {
        switch(logLevel)
        {
            case INFO:
                System.out.println("INFO :"+message);
                break;
            case DEBUG:
                System.out.println("DEBUG :"+message);
                break;
            case ERROR:
                System.out.println("ERROR :"+message);
                break;
        }
    }

    public enum LogLevel{
        INFO, 
        DEBUG,
        ERROR
    }
    
}

public class logger {
    Log log = Log.getInstance();
    public void logInfo(String message)
    {
        log.log(message, Log.LogLevel.INFO);
    }
    public void logDebug(String message)
    {
        log.log(message, Log.LogLevel.DEBUG);
    }
    public void logError(String message)
    {
        log.log(message, Log.LogLevel.ERROR);
    }
}
