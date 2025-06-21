package com.banking.src.Account;

import java.util.ArrayList;
import java.util.List;

import com.banking.src.Interface.IJointAccount;
import com.banking.src.Model.Customer;

public abstract class JointAccount extends Account implements IJointAccount {
    private List<Customer> jointOwners;

    public JointAccount(String accountNumber, Customer primaryOwner) {
        super(accountNumber, primaryOwner);
        this.jointOwners = new ArrayList<>();
    }

    @Override
    public void addJointOwner(Customer customer) {
        jointOwners.add(customer);
    }

    @Override
    public List<Customer> getJointOwners() {
        return jointOwners;
    }
   
    public double getBalance() {
        return super.balance;
    }
    protected void setBalance(double balance) {
        super.balance = balance;
    }

}
