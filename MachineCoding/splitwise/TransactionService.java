package splitwise;

import java.util.*;
import splitwise.User;
public class TransactionService {
    private Map<Integer, Transaction> transactionMap = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(int id, double amount, TransactionType type, User paidBy, List<User> users) {
        Transaction transaction = new Transaction(id, users, amount, type, paidBy);
        transactionMap.put(id, transaction);
        transactions.add(transaction);

        if (type == TransactionType.EQUAL) {
            double amountPerUser = amount / users.size();
            for (User u : users) {
                u.setAmount(u.getAmount() + amountPerUser);
            }
        } else if (type == TransactionType.PERCENT) {
            double totalAmount = 0;
            for (User u : users) {
                totalAmount += u.getAmount();
            }
            for (User u : users) {
                double amountPerUser = (amount * u.getAmount()) / totalAmount;
                u.setAmount(u.getAmount() + amountPerUser);
            }
        }
    }

    public void deleteTransaction(int id) {
        Transaction t1 = transactionMap.get(id);
        transactions.remove(t1);
    }

    public void updateTransaction(int id, double amount, TransactionType type, User paidBy, List<User> users) {
        Transaction t1 = transactionMap.get(id);
        t1.setAmount(amount);
        t1.setType(type);
        t1.setPaidBy(paidBy);
        t1.setUsers(users);
    }

    public void showAllTransactions() {
        for (Transaction t : transactions) {
            System.out.println("Transaction ID: " + t.getTransactionId());
            System.out.println("Amount: " + t.getAmount());
            System.out.println("Type: " + t.getType());
            System.out.println("Users: ");
        }
    }
}