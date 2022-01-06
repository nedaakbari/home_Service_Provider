package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.OrderDao;
import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.model.enumration.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
