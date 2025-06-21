// the task was to implement a Flipkart Buy Now Pay Later application. The key requirements were:

// Users should be assigned an initial credit limit.
// While placing an order, users can choose to either:
// Pay in cash
// Use the Pay Later feature
// Users get 30 days to clear dues.
// Users should be blacklisted if they default on payments 3 or more times.
// The problem required careful handling of edge cases like consecutive defaults and state management for user blacklisting.
import java.util.*;
class user{
    String id;
    double credit_limit;
    Boolean isBlackListed;
    static Integer Defaulter = 0;
    public user(String id, double credit_limit)
    {
        this.id = id;
        this.credit_limit = credit_limit;
        this.isBlackListed= false;
    }
}
enum PaymentType
{
    PAY_LATER,CASH
}
class Transaction 
{
    String id;
    PaymentType type;
    double amount;
    user user;
}
class PayLater extends Transaction
{
    Date dueDate;
}
class PaymentService
{
    Map<String,user>map_User;
    void addUser(String id,double credit_limit)
    {
        map_User.put(id,new user(id,credit_limit));
    }
    public user getUser(String id)
    {
        return map_User.get(id);
    }
    Boolean validate_Transaction(double amount,user u)
    {
        // 2 things the amount should not be more than the transaction limit and 
        if(u.credit_limit<amount)
        {
            return false;
        }else{
            if(u.isBlackListed)
            {
                return false;
            }
        }
        return true;
    }
    Boolean addTransaction(PaymentType type, double amount, user u)
    {
        if(type == PaymentType.CASH)
        {
            return true;
        }
        if(type == PaymentType.PAY_LATER)
        {
            if(validate_Transaction(amount, u))
            {
                return true;
            }
        }
        return false;
    }
}
public class Paylater {
    
}
