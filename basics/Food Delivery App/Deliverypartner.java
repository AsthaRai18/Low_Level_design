public class Deliverypartner {
    //assignment of delivery partner 
    long deliveryPartnerId;
    String name; 
    boolean isAvailable;
    public boolean accept()
    {
        if(isAvailable)
        {
            return true;
        }
        return false;
    }
}
