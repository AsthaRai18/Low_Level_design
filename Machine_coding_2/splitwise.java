import java.util.*;

public class splitwise {
    //expense sharing algo 
    // user - we can have and then 
    //total debt owe 
    //and to which user you owe 
    enum divCriteria
    {
        EQUAL, PERCENT, EXACT
    }
    class user{
        String userId;
        String name;
        String email;
        long mobileNumber;
        Map<user,Integer>debt;
        Map<user,Integer>owe;
    }
    class Transaction
    {
        String paidBy;
        double amount;
        HashSet<user>users;
        divCriteria criteria;
    }
    class TransactionService{
        void createExpense(user paidBy, int no_of_users, List<user>userList, divCriteria criteria, Map<user,Integer>mpUser)
        {
            if(criteria == divCriteria.EXACT)
            {
                
            }
        }
    }


}
