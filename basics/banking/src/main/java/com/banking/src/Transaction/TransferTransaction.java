package com.banking.src.Transaction;

import com.banking.src.Account.Account;
import com.banking.src.Enums.TransactionStatus;
import com.banking.src.Enums.TransactionType;

public class TransferTransaction extends Transaction {
    public TransferTransaction(double amount, Account sourceAccount, Account destinationAccount) {
        super(TransactionType.TRANSFER, amount, sourceAccount, destinationAccount);
    }

    @Override
    public boolean execute() {
        if (getSourceAccount().withdraw(getAmount())) {
            getDestinationAccount().deposit(getAmount());
            setStatus(TransactionStatus.COMPLETE);
            return true;
        }
        setStatus(TransactionStatus.FAILED);
        return false;
    }
}
