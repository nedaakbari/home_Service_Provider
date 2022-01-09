package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.dto.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    Mapper mapper = new Mapper();
    OrderDao orderDao;


/*    public void saveOrder(Orders orders) throws Exception {
        orders.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        long baseAmount = orders.getSubService().getBaseAmount();
        long proposedPrice = orders.getProposedPrice();
        if (proposedPrice >= baseAmount) {
            orderDao.save(orders);
        } else
            throw new Exception("your proposedPrice must be more than base amount of this service");
    }*/

  /*  public void update(Orders orders) {
        orderDao.update(orders);
    }

    public List<OrdersDto> findOrdersOfSubService(int subServiceId) {
        List<Orders> orders = orderDao.findOrdersOfSubService(subServiceId);
        return orders.stream().map(mapper::ordersDto).collect(Collectors.toList());
    }*/

    public Orders findOrderByID(int id) {
        Optional<Orders> foundOrder = orderDao.findOrderByID(id);
        if (foundOrder.isPresent()) {
            return foundOrder.get();
        } else
            throw new RuntimeException("❌❌❌ Error to find order ❌❌❌");
    }

   /* public List<OrdersDto> findOrderOfCustomer(Customer customer) {
        List<Orders> orderOfCustomer = orderDao.findOrderOfCustomer(customer.getId());
        return orderOfCustomer.stream().map(mapper::ordersDto).collect(Collectors.toList());
    }*/


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
