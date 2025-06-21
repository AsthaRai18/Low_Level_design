package com.banking.src.Account;

import java.util.ArrayList;
import java.util.List;

import com.banking.src.Interface.IAccount;
import com.banking.src.Model.Customer;
import com.banking.src.Transaction.DepositTransaction;
import com.banking.src.Transaction.Transaction;

public abstract class Account implements IAccount {
    private String accountNumber;
    protected double balance;
    private Customer owner;
    private List<Transaction> transactions;

    public Account(String accountNumber, Customer owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

      @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            DepositTransaction depositTransaction = new DepositTransaction(amount, this);
            transactions.add(depositTransaction);
            depositTransaction.execute();
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}