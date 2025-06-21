package com.banking.src.Model;

import java.util.ArrayList;
import java.util.List;

import com.banking.src.Account.Account;

public class Branch {
    private String branchId;
    private String location;
    private List<Account> accounts;
    private List<Employee> employees;

    public Branch(String branchId, String location) {
        this.branchId = branchId;
        this.location = location;
        this.accounts = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
}
