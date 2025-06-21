package com.banking.src.Interface;
import java.util.List;

import com.banking.src.Model.Customer;

public interface IJointAccount extends IAccount{
        void addJointAccountHolder(Customer customer);
        List<Customer> getJointAccountHolders();
        void addJointOwner(Customer customer);
            public List<Customer> getJointOwners();
}
