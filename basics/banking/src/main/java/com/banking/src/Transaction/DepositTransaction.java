package com.banking.src.Transaction;

import com.banking.src.Account.Account;
import com.banking.src.Enums.TransactionStatus;
import com.banking.src.Enums.TransactionType;

public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount, Account account) {
        super(TransactionType.DEPOSIT, amount, account, null);
    }

    @Override
    public boolean execute() {
        getSourceAccount().deposit(getAmount());
        setStatus(TransactionStatus.COMPLETE);
        return true;
    }
}
