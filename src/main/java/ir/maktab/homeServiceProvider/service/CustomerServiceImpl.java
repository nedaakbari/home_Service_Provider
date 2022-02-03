package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.enums.Role;
import ir.maktab.homeServiceProvider.data.enums.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.data.repository.CustomerRepository;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.service.exception.DuplicateData;
import ir.maktab.homeServiceProvider.service.exception.IncorrectInformation;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.exception.UserNotFoundException;
import ir.maktab.homeServiceProvider.service.interfaces.CustomerService;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerDao;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public CustomerDto register(CustomerDto customerDto) {//register
        Optional<Customer> foundUser = customerDao.findByUsernameAndPassword
                (customerDto.getUsername(), customerDto.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("this customer is already exist");
        } else {
            boolean isDuplicate = userService.isDuplicateEmail(customerDto.getEmail());
            if (!isDuplicate) {
                Customer customer = mapper.map(customerDto, Customer.class);
                customer.setStatus(UserRegistrationStatus.NEW);
                customer.setRole(Role.CUSTOMER);
                Customer save = customerDao.save(customer);
                return mapper.map(save, CustomerDto.class);
            } else
                throw new DuplicateData("this email is already exist");
        }
    }

    @Override
    public CustomerDto login(CustomerDto customerDto)  {
        Optional<Customer> customer = customerDao.findByEmailAndPassword(
                customerDto.getEmail(),customerDto.getPassword());
        if (customer.isPresent())
            return mapper.map(customer.get(),CustomerDto.class);
        else
            throw new NotFoundDta("no customer found with these email and pass");
    }


    @Override
    public void delete(CustomerDto customerDto) {
        Optional<Customer> customer = customerDao.findByEmail(customerDto.getEmail());
        if (customer.isPresent())
            customerDao.delete(customer.get());
        else
            throw new UserNotFoundException();
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerDao.findAll().stream()
                .map(customer -> mapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Integer theId) {
        Optional<Customer> found = customerDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), CustomerDto.class);
        else throw new NotFoundDta("no customer found");
    }

    @Override
    public CustomerDto getByEmail(String email) {
        Optional<Customer> found = customerDao.findByEmail(email);
        if (found.isPresent())
            return mapper.map(found.get(), CustomerDto.class);
        else throw new NotFoundDta("no customer found");
    }

    @Override
    public void updatePassword(String newPassword, String oldPassword, CustomerDto customerDto) {
        if (customerDto.getPassword().equals(oldPassword)) {
            customerDao.updatePassword(customerDto.getEmail(), newPassword);
        } else
            throw new IncorrectInformation("incorrect password");
    }

    @Override
    public void updateCreditCart(double amount, CustomerDto customerDto) {
        customerDao.updateCreditCart(customerDto.getEmail(), amount);
    }

    @Override
    public CustomerDto findCustomerByUseAndPass(String username, String password) {
        Optional<Customer> customer = customerDao.findByUsernameAndPassword(username, password);
        if (customer.isPresent()) {
            return mapper.map(customer.get(), CustomerDto.class);
        } else
            throw new NotFoundDta("no customer found with these use and pass");
    }


}
