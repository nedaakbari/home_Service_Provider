package ir.maktab.homeServiceProvider.dto.mapper;


import ir.maktab.homeServiceProvider.entity.Person.Customer;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .username(customerDto.getUsername())
                .password(customerDto.getPassword())
                .email(customerDto.getEmail())
                .build();
    }

    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .email(customer.getEmail())
                .build();


    }
}
