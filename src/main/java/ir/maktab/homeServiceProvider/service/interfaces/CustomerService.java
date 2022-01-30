package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.Person.Customer;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.service.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    CustomerDto register(CustomerDto customerDto);

    Customer login(CustomerDto customerDto) throws CustomerNotFoundException;

    void delete(CustomerDto customerDto);

    List<CustomerDto> getAll();

    CustomerDto getById(Integer theId);

    CustomerDto getByEmail(String email);

   // void updatePassword(String newPassword, String oldPassword, String email);
    void updatePassword(String newPassword, String oldPassword, CustomerDto customerDto);

    CustomerDto findCustomerByUseAndPass(String username, String password);

    void updateCreditCart(double amount, CustomerDto customerDto);

}
