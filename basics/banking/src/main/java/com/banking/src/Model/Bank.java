package com.banking.src.Model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
        private String name;
    private List<Branch> branches;
    private List<Customer> customers;
    private List<Employee> employees;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

}
