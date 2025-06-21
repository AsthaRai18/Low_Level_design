package AlarmSystem;

public class AlertEvaluator {
    
    public boolean evaluate(Alert alert, double value) {
        switch (alert.getAlertType()) {
            case USAGE:
                return value > alert.getThreshold();
            case LATENCY:
                return value > alert.getThreshold();
            case ERROR:
                return value > alert.getThreshold();
            default:
                return false;
        }
    }
}
