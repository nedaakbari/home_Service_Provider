package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;

import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.service.OfferServiceImpl;
import ir.maktab.homeServiceProvider.service.OrderServiceImpl;
import ir.maktab.homeServiceProvider.service.SubCategoryServiceImpl;
import ir.maktab.homeServiceProvider.service.UserServiceImpl;
import ir.maktab.homeServiceProvider.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;

public class SaveOfferTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    OrderServiceImpl orderService = context.getBean(OrderServiceImpl.class);
    SubCategoryServiceImpl service = context.getBean(SubCategoryServiceImpl.class);
    OfferServiceImpl offerService = context.getBean(OfferServiceImpl.class);
    UserServiceImpl userService = context.getBean(UserServiceImpl.class);
    Offer offer;
    Orders orders;

    @BeforeEach
    void init() throws ParseException {
        Customer customer = (Customer) userService.findUserByUseAndPass("neda_ak", "Neda@222");
        Expert expert = (Expert) userService.getById(3);
        SubCategory subCategory = service.getById(7);
        orders = Orders.builder()
                .description("hg").proposedPrice(100000.0)
                .subCategory(subCategory).doWorkDate(DateUtil.convertStringToDate("2022-01-09"))
                .customer(customer).build();

        offer = new Offer();
        offer.setProposedPrice(subCategory.getBasePrice() - 50);
        offer.setExpert(expert);
    }

    @Test
    void giveLessAmount_saveOrder_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                offerService.saveOffer(offer, orders));
        Assertions.assertEquals("offerPrice must not be lower than baseAmount of this subService", result);
    }

}
