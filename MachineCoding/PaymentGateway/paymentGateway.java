package PaymentGateway;
import java.security.PublicKey;
import java.util.*;

import org.xml.sax.HandlerBase;
enum PaymentMode
{
    NETBANKING, 
    UPI, 
    DEBIT_CARD
}
class PaymentRequest
{
    String clientId;
    PaymentMode mode;
    double amount;
    // Additional instrument details (card, UPI, netbanking, etc.)
    Map<String, String> details;
    
    public PaymentRequest(String clientId, PaymentMode mode, double amount, Map<String, String> details) {
        this.clientId = clientId;
        this.mode = mode;
        this.amount = amount;
        this.details = details;
    }
}
class PaymentResponse
{
    boolean success;
    String message;
    public PaymentResponse(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }
}
 interface Bank
{
    String getName();
    PaymentResponse processPayment(PaymentRequest request);
}
class HDFCBank implements Bank {
    private java.util.Random random = new java.util.Random();
    
    @Override
    public String getName() {
        return "HDFC";
    }
    
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        boolean success = random.nextBoolean();
        return new PaymentResponse(success, "Processed by HDFC, " + (success ? "Payment Successful" : "Payment Failed"));
    }
}

class ICICIBank implements Bank {
    private java.util.Random random = new java.util.Random();
    
    @Override
    public String getName() {
        return "ICICI";
    }
    
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        boolean success = random.nextBoolean();
        return new PaymentResponse(success, "Processed by ICICI, " + (success ? "Payment Successful" : "Payment Failed"));
    }
}

class SBIBank implements Bank {
    private java.util.Random random = new java.util.Random();
    
    @Override
    public String getName() {
        return "SBI";
    }
    
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        boolean success = random.nextBoolean();
        return new PaymentResponse(success, "Processed by SBI, " + (success ? "Payment Successful" : "Payment Failed"));
    }
}
class BankDistribution {
    Bank bank;
    int percentage; // percentage allocation
    
    public BankDistribution(Bank bank, int percentage) {
        this.bank = bank;
        this.percentage = percentage;
    }
}
class PaymentRouter {
    // Map from PaymentMode to list of BankDistribution rules.
    private Map<PaymentMode, List<BankDistribution>> routingMap = new HashMap<>();
    
    public void setRouting(PaymentMode mode, List<BankDistribution> distributions) {
        routingMap.put(mode, distributions);
    }
    
    // Weighted random selection based on the percentage distribution.
    public Bank route(PaymentMode mode) {
        List<BankDistribution> list = routingMap.get(mode);
        if (list == null || list.isEmpty()) {
            return null;
        }
        int totalPercentage = list.stream().mapToInt(bd -> bd.percentage).sum();
        int rand = new Random().nextInt(totalPercentage) + 1; // 1 to totalPercentage
        int sum = 0;
        for (BankDistribution bd : list) {
            sum += bd.percentage;
            if (rand <= sum) {
                return bd.bank;
            }
        }
        return list.get(list.size() - 1);
    }
    
    public String showDistribution() {
        StringBuilder sb = new StringBuilder();
        for (PaymentMode mode : routingMap.keySet()) {
            sb.append("PaymentMode: ").append(mode).append("\n");
            for (BankDistribution bd : routingMap.get(mode)) {
                sb.append("   Bank: ").append(bd.bank.getName())
                  .append(" => ").append(bd.percentage).append("%\n");
            }
        }
        return sb.toString();
    }
}
class Abc {
    int id;
    String name;
    
    public Abc(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class paymentGateway {
    public static void main(String[] args) {
        List<Abc> abcList = new ArrayList<>();
        abcList.add(new Abc(1, "a"));
        abcList.add(new Abc(3, "c"));
        abcList.add(new Abc(2, "b"));
        
        // Sorting using a lambda expression
        Collections.sort(abcList, (Abc a1, Abc a2) -> a1.id - a2.id);
        List<Integer>it = new ArrayList<>();
        Collections.sort(it,Comparator.reverseOrder());
        // Printing the sorted list
        for (Abc a : abcList) {
            System.out.println(a.id + " " + a.name);
        }
        Map<String,Integer>mp = new HashMap<>();
    }
}
