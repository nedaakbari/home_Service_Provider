package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CustomerDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class CustomerServiceImpl /*implements CustomerService*/ {
    private ModelMapper mapper = new ModelMapper();
    private final CustomerDao customerDao;
/*    private final OrderServiceImpl orderService;
    private final OfferServiceImpl offerService;*/

    public void save(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("this customer is already exist");
        } else {
            customer.setStatus(UserRegistrationStatus.NEW);
            customerDao.save(customer);
        }
    }

    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    public List<CustomerDto> getAll() {
        return customerDao.findAll().stream()
                .map(customer -> mapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    public Customer getById(Integer theId) {
        Optional<Customer> found = customerDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no customer found");
    }

    public void updatePassword(String newPassword, Customer customer) {
        customer.setPassword(newPassword);
        customerDao.save(customer);
    }

    public void updateCreditCart(double amount, Customer customer) {
        customer.setCreditCart(amount);
        customerDao.save(customer);
    }

    public Customer findCustomerByUseAndPass(String username, String password) {
        Optional<Customer> customer = customerDao.findByUsernameAndPassword(username, password);
        if (customer.isPresent()) {
            return customer.get();
        } else
            throw new NotFoundDta("no customer found with these use and pass");
    }

}
