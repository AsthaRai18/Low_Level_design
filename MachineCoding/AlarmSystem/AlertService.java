package AlarmSystem;

import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
public class AlertService {
    //create alert , delete alert , update alert
    private Map<String,Alert> alertMap = new ConcurrentHashMap<>();
    public void createAlert(Alert alert){
        alertMap.put(alert.getAlertId().toString(), alert);
    }
    public void deleteAlert(Alert alert)
    {
        alertMap.remove(alert.getAlertId().toString());
    }
    public void updateAlert(Alert alert)
    {
        alertMap.put(alert.getAlertId().toString(),alert);
    }
    public Alert getAlert(String alertId)
    {
        return alertMap.get(alertId);
    }
    public List<Alert> getAllAlerts()
    {
        return new ArrayList<>(alertMap.values());
    }
}
