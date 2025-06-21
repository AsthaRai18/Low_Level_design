package com.banking.Banking.System.Transaction;

import com.banking.Banking.System.Account.Account;
import com.banking.Banking.System.enums.TransactionStatus;
import com.banking.Banking.System.enums.TransactionType;

public class TransferTransaction extends Transaction {
    public TransferTransaction(double amount, Account sourceAccount, Account destinationAccount) {
        super(TransactionType.TRANSFER, amount, sourceAccount, destinationAccount);
    }

    @Override
    public boolean execute() {
        if (getSourceAccount().withdraw(getAmount())) {
            getDestinationAccount().deposit(getAmount());
            setStatus(TransactionStatus.SUCCESS);
            return true;
        }
        setStatus(TransactionStatus.FAILED);
        return false;
    }
}