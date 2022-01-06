package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.model.entity.Offer;
import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;

public class SaveOfferTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    OrderService orderService = context.getBean(OrderService.class);
    SubServiceService service = context.getBean(SubServiceService.class);
    OfferService offerService = context.getBean(OfferService.class);
    UserService userService = context.getBean(UserService.class);
    Offer offer;
    Orders orders;

    @BeforeEach
    void init() throws ParseException {
        Customer customer = (Customer) userService.findUserByUseAndPass("neda_ak", "Neda@222");
        Expert expert = (Expert) userService.findUserById(3);
        SubService subService = service.findById(7);
        orders = Orders.builder().description("hg").proposedPrice(100000).subService(subService).workDay(DateUtil.convertStringToDate("2022-01-09")).customer(customer).build();
        offer = new Offer();
        offer.setProposedPriceOffer(subService.getBaseAmount() - 50);
        offer.setExpert(expert);

    }

    @Test
    void giveLessAmount_saveOrder_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                offerService.saveOffer(offer, orders));
        Assertions.assertEquals("offerPrice must not be lower than baseAmount of this subService", result);
    }

}
