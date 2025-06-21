import java.util.List;

public class MatchMaking {
    private List<Driver>availableDrivers;
    double THRESHOLD_DISTANCE = 10;
    public MatchMaking(List<Driver>availableDrivers)
    {
        this.availableDrivers = availableDrivers;
    }
    public Driver matchDriver(Location location)
    {
        Driver matchedDriver = null;
        for(Driver driver : availableDrivers)
        {
            if(driver.isAvailable()== true && driver.getDriverLocation().distance(location) < THRESHOLD_DISTANCE)
            {
                matchedDriver = driver;
                break;
            }
        }
        return matchedDriver;
    }
}
