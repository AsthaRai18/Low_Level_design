// Select a pay mode
// Enter details
// For netbanking - enter username and password
// For credit / debit card - enter card details ( number, expiry, cvv etc )
// UPI - enter vpa

// addClient() - add a client in PG
// removeClient() - remove client from PG
// hasClient() - does a client exist in the PG?
// listSupportedPaymodes() - show paymodes support by PG. if a client is passed as parameter, all supported paymodes for the clients should be shown.
// addSupportForPaymode() - add paymodes support in the PG. If a client is passed as parameter, add paymode for the clients.
// removePaymode() - remove paymode ( both from PG or client basis parameter)
// showDistribution() - show active distribution percentage of router
// makePayment( //necessary payment details )
import java.sql.ClientInfoStatus;
import java.util.*;
class client{
    String name;
    Integer clientId;
    public client(String name, Integer clInteger)
    {
        this.name  = name;
        this.clientId = clInteger;
    }
    List<paymentMode>paymentModes;
}

enum paymentMode{
    UPI, netbanking, creditCard
}
abstract class PaymentGateway
{
    String paymentMode;
}
class netbanking extends PaymentGateway{
    String userName;
    String password;
}
class creditCard extends PaymentGateway
{
    long Number;
    String expiry;
    Integer cvv;
}
class UPI extends PaymentGateway
{
    String vpa;
}
class PaymentGatewayService
{
    Set<client>clientSet;
    List<paymentMode>paymentModes;
    PaymentGatewayService()
    {
        clientSet = new HashSet<>();
    }
    List<PaymentGateway>paymentGateways = new ArrayList<>();
    //add 
    //remove 
    //has
    public void addClient(client tClient)
    {
        clientSet.add(tClient);
    }
    public void removeClient(client tClient)
    {
        clientSet.remove(tClient);
    }
    public Boolean hasClient(client tClient)
    {
        if(clientSet.contains(tClient))
        {
            return true;
        }else{
            return false;
        }
    }
    public void addPaymentMode()
    {

   }
}
class Sample
{
    volatile Sample instancr;
    public Sample getSample()
    {
        if(instancr == null)
        {
            return new Sample();
        }
    }

}

public class PaymentGateway{

}