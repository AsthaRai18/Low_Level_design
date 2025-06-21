package com.banking.src.Transaction;

import com.banking.src.Enums.TransactionStatus;
import com.banking.src.Enums.TransactionType;
import com.banking.src.Interface.Itransaction;

import java.util.UUID;

import com.banking.src.Account.Account;
public abstract class Transaction implements Itransaction {
    private String transactionId;
    private TransactionType transactionType;
    private double transactionAmount;
    private Account sourceAccount;
    private Account destinationAccount;
    private TransactionStatus transactionStatus;
    // ransactionType.TRANSFER, amount, sourceAccount, destinationAccount
    public Transaction( TransactionType transfer, double transactionAmount, Account sourceAccount, Account destinationAccount) {
        this.transactionId = UUID.randomUUID().toString();
        this.transactionType = transfer;
        this.transactionAmount = transactionAmount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.transactionStatus = TransactionStatus.PENDING;
    }
    public TransactionStatus getStatus() {
        return transactionStatus;
    }

    public void setStatus(TransactionStatus status) {
        this.transactionStatus = status;
    }

    public double getAmount() {
        return transactionAmount;
    }
    

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    
}
