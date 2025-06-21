package com.banking.Banking.System.Interfaces;

public interface IJointAccount extends IAccount {
    void addJointOwner(Customer customer);
    List<Customer> getJointOwners();
}
