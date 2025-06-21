package AlarmSystem;
import java.util.*;
public class StorageService {
    private List<String>alertLogs;
    public void logAlert(String alert)
    {
        alertLogs.add(alert);
    }
    public List<String>getAllLogs()
    {
        return alertLogs;
    }
}
