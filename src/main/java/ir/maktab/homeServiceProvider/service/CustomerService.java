package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.CustomerDao;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    private CustomerDao customerDao;

    public void saveCustomer(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUseAndPass(customer.getUsername(), customer.getPassword());
        if (foundUser.isPresent()) {
            throw new RuntimeException("this customer is already exist");
        } else {
            customerDao.save(customer);
        }
    }

    public void deleteCustomer(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUseAndPass(customer.getUsername(), customer.getPassword());
        if (foundUser.isPresent()) {
            customerDao.delete(customer);
        } else {
            throw new RuntimeException("there is no customer with these info");
        }
    }

    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    public Customer findCustomerByUseAndPass(String username, String password) {
        Optional<Customer> customer = customerDao.findByUseAndPass(username, password);
        if (customer.isPresent()) {
            return customer.get();
        } else
            throw new RuntimeException("no customer found with these use and pass");
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    //region setter & getter & constructor
    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    //endregion
}
