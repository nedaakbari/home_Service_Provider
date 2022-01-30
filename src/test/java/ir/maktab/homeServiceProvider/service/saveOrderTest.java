package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.service.CustomerServiceImpl;
import ir.maktab.homeServiceProvider.service.SubCategoryServiceImpl;
import ir.maktab.homeServiceProvider.service.interfaces.OrderService;
import ir.maktab.homeServiceProvider.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;

public class saveOrderTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    OrderService orderService = context.getBean(OrderService.class);
    SubCategoryServiceImpl service = context.getBean(SubCategoryServiceImpl.class);
    CustomerServiceImpl customerService = context.getBean(CustomerServiceImpl.class);
    Orders orders;

    @BeforeEach
    void init() throws ParseException {
        Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        SubCategory homeSpraying = service.getById(7);

        Address address = new Address();
        address.setCity("shiraz");
        address.setStreet("engheleb");
        address.setZipCode("4785p");

        orders=Orders.builder().address(address).description("hg").proposedPrice(100000.0)
                .subCategory(homeSpraying).doWorkDate(DateUtil.convertStringToDate("2022-01-09"))
                .customer(neda).build();
    }

    @Test
    void giveLessAmount_saveOrder_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                orderService.save(orders));
        Assertions.assertEquals("your proposedPrice must be more than base amount of this service", result);
    }

}
