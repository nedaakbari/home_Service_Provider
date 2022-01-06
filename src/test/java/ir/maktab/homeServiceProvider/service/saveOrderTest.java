package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.Address;
import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.util.Date;

public class saveOrderTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    OrderService orderService = context.getBean(OrderService.class);
    SubServiceService service = context.getBean(SubServiceService.class);
    CustomerService customerService = context.getBean(CustomerService.class);
    Orders orders;

    @BeforeEach
    void init() throws ParseException {
        Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        SubService homeSpraying = service.findById(7);
        Address address = new Address();
        address.setCity("shiraz");
        address.setStreet("engheleb");
        address.setZipCode("4785p");
        orders=Orders.builder().address(address).description("hg").proposedPrice(100000).subService(homeSpraying).workDay(DateUtil.convertStringToDate("2022-01-09")).customer(neda).build();
    }

    @Test
    void giveLessAmount_saveOrder_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                orderService.saveOrder(orders));
        Assertions.assertEquals("your proposedPrice must be more than base amount of this service", result);
    }

}
