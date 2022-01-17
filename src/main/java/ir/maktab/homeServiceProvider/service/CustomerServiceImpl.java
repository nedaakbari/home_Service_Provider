package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CustomerDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.dto.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {
    private final CustomerMapper mapper;
    private final CustomerDao customerDao;

    public void saveCustomer(Customer customer) {
        // customer.setStatus(UserRegistrationStatus.NEW);
        customerDao.save(customer);
    }

    public void removeCustomer(Customer customer) {
        customerDao.delete(customer);
    }

    @Transactional
    public void UpdatePassword(String newPassword, int id) {
        customerDao.updatePassword(newPassword, id);
    }
/*
    public void saveCustomer(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (foundUser.isPresent()) {
            throw new RuntimeException("this customer is already exist");
        } else {
            customerDao.save(customer);
        }
    }

    public void deleteCustomer(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (foundUser.isPresent()) {
            customerDao.delete(customer);
        } else {
            throw new RuntimeException("there is no customer with these info");
        }
    }

 */
/*   public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }*//*


    public Customer findCustomerByUseAndPass(String username, String password) {
        Optional<Customer> customer = customerDao.findByUsernameAndPassword(username, password);
        if (customer.isPresent()) {
            return customer.get();
        } else
            throw new RuntimeException("no customer found with these use and pass");
    }

    public Iterable<Customer> findAll() {
        return customerDao.findAll();
    }
*/
}
