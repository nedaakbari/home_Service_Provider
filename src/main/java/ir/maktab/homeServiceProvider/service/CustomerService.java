package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;

import java.util.List;

public interface CustomerService {

    public List <Customer> getCustomers();

    public void saveCustomer(Customer theCustomer);

    public Customer getCustomer(int theId);

    public void deleteCustomer(int theId);

}