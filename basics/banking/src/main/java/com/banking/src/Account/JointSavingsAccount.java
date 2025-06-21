package com.banking.src.Account;

import java.util.List;

import com.banking.src.Interface.IAccount;
import com.banking.src.Model.Customer;

public class JointSavingsAccount extends JointAccount {
    private static final double WITHDRAWAL_LIMIT = 10000.0;

    public JointSavingsAccount(String accountNumber, Customer primaryOwner) {
        super(accountNumber, primaryOwner);
    }

    @Override
    public synchronized boolean withdraw(double amount) {
        if (amount > WITHDRAWAL_LIMIT || amount > getBalance()) {
            return false;
        }
        setBalance(getBalance() - amount); // Deduct amount
        return true;
    }

    @Override
    public void addJointAccountHolder(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addJointAccountHolder'");
    }

    @Override
    public List<Customer> getJointAccountHolders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJointAccountHolders'");
    }

    @Override
    public double checkBalance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkBalance'");
    }

    @Override
    public void transfer(double amount, IAccount targeIAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }
}