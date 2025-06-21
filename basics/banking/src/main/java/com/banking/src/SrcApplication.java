package com.banking.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.banking.src.Account.JointSavingsAccount;
import com.banking.src.Model.Bank;
import com.banking.src.Model.Branch;
import com.banking.src.Model.Customer;

@SpringBootApplication
public class SrcApplication {

	public static void main(String[] args) {
		Bank bank = new Bank("MyBank");

        // Create Branch
        Branch branch = new Branch("B001", "New York");
        bank.addBranch(branch);

        // Create Customers
        Customer customer1 = new Customer("C001", "John Doe");
        Customer customer2 = new Customer("C002", "Jane Doe");
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);

        // Create Joint Account
        JointSavingsAccount jointSavingsAccount = new JointSavingsAccount("JSA001", customer1);
        jointSavingsAccount.addJointOwner(customer2);
        customer1.openAccount(jointSavingsAccount);
        customer2.openAccount(jointSavingsAccount);

        // Perform Concurrent Transactions
        Runnable transaction1 = () -> {
            jointSavingsAccount.deposit(5000);
            System.out.println("Customer 1 deposited 5000. Balance: " + jointSavingsAccount.getBalance());
        };

        Runnable transaction2 = () -> {
            jointSavingsAccount.withdraw(2000);
            System.out.println("Customer 2 withdrew 2000. Balance: " + jointSavingsAccount.getBalance());
        };

        Thread thread1 = new Thread(transaction1);
        Thread thread2 = new Thread(transaction2);

        thread1.start();
        thread2.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final Balance
        System.out.println("Final Balance: " + jointSavingsAccount.getBalance());

		SpringApplication.run(SrcApplication.class, args);
	}

}
