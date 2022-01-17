package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CustomerDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.dto.mapper.CustomerMapper;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements Services<Customer, CustomerDto, Integer> {
    private final CustomerMapper mapper;
    private final CustomerDao customerDao;

    @Override
    public void save(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("this customer is already exist");
        } else {
            customer.setStatus(UserRegistrationStatus.NEW);
            customerDao.save(customer);
        }
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> allCustomer = customerDao.findAll();
        return allCustomer.stream().map(mapper::customerToDto).collect(Collectors.toList());
    }

    @Override
    public Customer getById(Integer theId) {
        Optional<Customer> found = customerDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no customer found");
    }

    @Transactional
    public void UpdatePassword(String newPassword, int id) {
        customerDao.updatePassword(newPassword, id);
    }


    public Customer findCustomerByUseAndPass(String username, String password) {
        Optional<Customer> customer = customerDao.findByUsernameAndPassword(username, password);
        if (customer.isPresent()) {
            return customer.get();
        } else
            throw new RuntimeException("no customer found with these use and pass");
    }

}
