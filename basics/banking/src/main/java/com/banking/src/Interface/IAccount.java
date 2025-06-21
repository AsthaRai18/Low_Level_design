package com.banking.src.Interface;

public interface IAccount {
    void deposit(double amount);
    boolean withdraw(double amount); //this one is boolean as we dont know like f the withdrawa is successful or not 
    double checkBalance();
    void transfer(double amount, IAccount targeIAccount);
}
