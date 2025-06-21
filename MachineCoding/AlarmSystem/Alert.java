package AlarmSystem;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
public class Alert {
    private AtomicInteger alertId;
    private String AlertName;
    private AlertType alertType;
    private double Threshold;
    private String action;
    private List<String>recipient;
    public Alert(AtomicInteger alertId, String alertName, AlertType alertType, double threshold, String action, List<String> recipient) {
        this.alertId = alertId;
        AlertName = alertName;
        this.alertType = alertType;
        Threshold = threshold;
        this.action = action;
        this.recipient = recipient;
    }
    public AtomicInteger getAlertId() {
        return alertId;
    }
    public void setAlertId(AtomicInteger alertId) {
        this.alertId = alertId;
    }
    public String getAlertName() {
        return AlertName;
    }
    public void setAlertName(String alertName) {
        AlertName = alertName;
    }
    public AlertType getAlertType() {
        return alertType;
    }
    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }
    public double getThreshold() {
        return Threshold;
    }
    public void setThreshold(double threshold) {
        Threshold = threshold;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public List<String> getRecipient() {
        return recipient;
    } 
}
