package com.banking.Banking.System.Transaction;

public abstract class Transaction implements ITransaction {
    private String transactionId;
    private TransactionType type;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
    private TransactionStatus status;

    public Transaction(TransactionType type, double amount, Account sourceAccount, Account destinationAccount) {
        this.transactionId = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.status = TransactionStatus.PENDING;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    protected void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }
}
T