package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl /*implements OrderService*/ {
    private ModelMapper mapper = new ModelMapper();
    private final OrderDao orderDao;

    public void save(Orders orders) {
        orders.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        orderDao.save(orders);
    }

    public void update(Orders orders) {
        orderDao.save(orders);
    }


/*    public void save(SubCategory subCategory, double proposePrice, String description, Address address,
                     Date workDate) {

        Orders orders = new Orders();
        orders.setDoWorkDate(workDate);
        orders.setSubCategory(subCategory);
        orders.setProposePrice(proposePrice);
        orders.setDescription(description);
        orders.setAddress(address);
        orders.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        orderDao.save(orders);
    }*/ //todo which one is better?

    public void delete(Orders orders) {
        orderDao.delete(orders);
    }

    public List<OrdersDto> getAll() {
        return orderDao.findAll().stream()
                .map(orders -> mapper.map(orders, OrdersDto.class))
                .collect(Collectors.toList());
    }

    public Orders getById(Long theId) {
        Optional<Orders> foundOrder = orderDao.findById(theId);
        if (foundOrder.isPresent())
            return foundOrder.get();
        else throw new NotFoundDta("❌❌❌ Error, no order found ❌❌❌ ");
    }


    public List<OrdersDto> findOrdersOfSubService(int subServiceId) {
        List<Orders> orders = orderDao.findOrdersOfSubService(subServiceId);//findOrdersOfSubService
         return orders.stream().map(item -> mapper.map(item,OrdersDto.class)).collect(Collectors.toList());
    }

    public List<OrdersDto> findOrderOfCustomer(Customer customer) {
        List<Orders> orderOfCustomer = orderDao.findOrderOfCustomer(customer.getId());
        return orderOfCustomer.stream().map(item->mapper.map(item,OrdersDto.class)).collect(Collectors.toList());
    }

    public Offer findAcceptedOfferOfOrder(Orders order) {
        Offer acceptedOffer = null;
        if (order.getState().equals(OrderState.PAID)) {
            Set<Offer> offers = order.getOffers();
            for (Offer offer : offers) {
                if (offer.getStatus().equals(OfferStatus.ACCEPTED)) {
                    acceptedOffer = offer;
                }
            }
            return acceptedOffer;
        } else {
            throw new RuntimeException("Order not Paid!");
        }
    }



}
