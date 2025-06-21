package splitwise;
import java.util.*;
public class Transaction {
    private int TransactionId;
    private List<User> users;
    double amount;
    private TransactionType type;
    private User paidBy;
    public Transaction(int id,List<User> users,double amount,TransactionType type,User paidBy){
        this.TransactionId=id;
        this.users=users;
        this.amount=amount;
        this.type=type;
        this.paidBy=paidBy;
    }
    public int getTransactionId() {
        return TransactionId;
    }
    public void setTransactionId(int transactionId) {
        TransactionId = transactionId;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public User getPaidBy() {
        return paidBy;
    }
    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }
}
