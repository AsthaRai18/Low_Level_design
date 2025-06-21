package com.banking.src.Model;

import com.banking.src.Enums.TransactionStatus;
import com.banking.src.Interface.IEmployee;
import com.banking.src.Transaction.Transaction;

public class Employee implements IEmployee {
    private String employeeId;
    private String name;
    private String role;

    public Employee(String employeeId, String name, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
    }

    @Override
    public void approveTransaction(Transaction transaction) {
        if (transaction.getStatus() == TransactionStatus.PENDING) {
            transaction.setStatus(TransactionStatus.COMPLETE);
        }
    }
}
