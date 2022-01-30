
package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CustomerServiceImpl customerService = context.getBean(CustomerServiceImpl.class);

    Customer customer;

    @BeforeEach
    void init() {
        customer = Customer.builder().firstName("ali").lastName("rezaee").email("aliRezaee@gmail.com").role(Role.CUSTOMER).username("ali123")
                .password("ali@7895").status(UserRegistrationStatus.WAITING_FOR_CONFIRM)
                .phoneNumber("9178542569").build();
    }

    /**
     * Test method for {customerService#saveCustomer()}.
     */
    @Test
    void save() {
        customer = Customer.builder().firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").status(UserRegistrationStatus.WAITING_FOR_CONFIRM)
                .phoneNumber("09370730398").email("neda@gmail.com")
                .creditCart(1000.0)
                .build();
        customerService.save(customer);
    }

    @Test
    void giveDuplicateCustomer_findCustomerByUseAndPass_ThrowException() {
        customer = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.save(customer));
        Assertions.assertEquals("this customer is already exist", result.getMessage());
    }

    @Test
    void deleteNoExistCustomer_deleteCustomer_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.delete(customer));
        Assertions.assertEquals("there is no customer with these info", result.getMessage());
    }

    @Test
    void NoExistCustomer_findCustomerByUseAndPass_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.findCustomerByUseAndPass(customer.getUsername(), customer.getPassword()));
        Assertions.assertEquals("no customer found with these use and pass", result.getMessage());
    }

    @Test
    public void getData_getById_notBeNull() {
        customer = customerService.getById(1);
        assertNotNull(customer);
    }

    @Test
    public void getByIdIsNotExist_getById_throwException() {
        assertThrows(NotFoundDta.class, () -> customerService.getById(10));
    }

    @Test
    public void getALlCustomer_getAll_findSizeToCompare() {
        List<CustomerDto> getAll = customerService.getAll();
        assertEquals(1, getAll.size());
    }
}

 /*   @Test
    void decreaseCreditOk() {
        Customer customer = customerService.decreaseCredit(1, 5000.0);
        assertEquals(10000.0, customer.getCredit());
    }

    @Test
    void decreaseCreditNotEnough() {
        assertThrows(NotEnoughException.class, () -> customerService.decreaseCredit(1, 15000.0));
    }*/



/**
 * Test method for {customerService#saveCustomer()}.
 *//*

 */
/*  @Test
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
    }*//*



}
*/
