package com.banking.Banking.System.Interfaces;

public interface IAccount {
    void deposit(double amount);
    boolean withdraw(double amount);
    double getBalance();
    void addTransaction(Transaction transaction);
}
