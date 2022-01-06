package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CustomerService customerService = context.getBean(CustomerService.class);

    Customer customer;

    @BeforeEach
    void init() {
        customer = Customer.builder().name("ali").lastName("rezaee").email("aliRezaee@gmail.com").role(Role.CUSTOMER).username("ali123")
                .password("ali@7895").status(UserRegistrationStatus.WAITING_FOR_CONFIRM)
                .phoneNumber("9178542569").build();
    }

    @Test
    void giveDuplicateCustomer_findCustomerByUseAndPass_ThrowException() {
        customer = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.saveCustomer(customer));
        Assertions.assertEquals("this customer is already exist", result.getMessage());
    }
    @Test
    void deleteNoExistCustomer_deleteCustomer_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.deleteCustomer(customer));
        Assertions.assertEquals("there is no customer with these info", result.getMessage());
    }

    @Test
    void NoExistCustomer_findCustomerByUseAndPass_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.findCustomerByUseAndPass(customer.getUsername(),customer.getPassword()));
        Assertions.assertEquals("no customer found with these use and pass", result.getMessage());
    }

    /**
     * Test method for {customerService#saveCustomer()}.
     */
    @Test
    void giveDuplicateCustomer_findCustomerByUseAndPass_ThrowExceptions() {
        Customer foundCustomer = null;
        try {
            foundCustomer = customerService.findCustomerByUseAndPass(customer.getUsername(), customer.getPassword());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (foundCustomer == null) {
            customerService.saveCustomer(customer);
            customerService.saveCustomer(customer);
            RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                    customerService.saveCustomer(customer));
            Assertions.assertEquals("this customer is already exist", result.getMessage());
        } else
            customerService.saveCustomer(foundCustomer);
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.saveCustomer(customer));
        Assertions.assertEquals("this customer is already exist", result.getMessage());
    }


}
