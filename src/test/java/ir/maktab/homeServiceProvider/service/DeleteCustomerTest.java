package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DeleteCustomerTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CustomerService customerService = context.getBean(CustomerService.class);

    Customer customer;

    @BeforeEach
    void init() {
        customer=Customer.builder().name("mona").lastName("razbani").email("mona@gmail.com").phoneNumber("9174567885")
                .username("mona@147").password("mona_123").role(Role.CUSTOMER).status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
    }

    @Test
    void giveNoCustomer_deleteMethode_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.deleteCustomer(customer));
        Assertions.assertEquals("there is no customer with these info",result.getMessage());
    }
}
