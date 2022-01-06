package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.OrderDao;
import ir.maktab.homeServiceProvider.model.dto.OrdersDto;
import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderService {
    OrderDao orderDao;


    public void saveOrder(Orders orders) throws Exception {
        orders.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        long baseAmount = orders.getSubService().getBaseAmount();
        long proposedPrice = orders.getProposedPrice();
        if (proposedPrice >= baseAmount) {
            orderDao.save(orders);
        } else
            throw new Exception("your proposedPrice must be more than base amount of this service");
    }

    public List<OrdersDto> findOrdersOfSubService(int subServiceId) {
        List<Orders> orders = orderDao.findOrdersOfSubService(subServiceId);
        Mapper mapper = new Mapper();
        return orders.stream().map(mapper::OrdersDto).collect(Collectors.toList());
    }

    public Orders findOrderByID(int id) {
        Optional<Orders> foundOrder = orderDao.findOrderByID(id);
        if (foundOrder.isPresent()) {
            return foundOrder.get();
        } else
            throw new RuntimeException("❌❌❌ Error to find order ❌❌❌");
    }

    //region setter & getter & constructor
    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    //endregion
}
