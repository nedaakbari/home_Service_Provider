package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.dto.mapper.OrderMapper;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements Services<Orders, OrdersDto, Long> {
    private final OrderMapper mapper;
    private final OrderDao orderDao;
    private final OfferDao offerDao;
    private final OfferService offerService;

    @Override
    public void save(Orders orders) {
        orders.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        orderDao.save(orders);
    }

    @Override
    public void delete(Orders orders) {
        orderDao.delete(orders);

    }

    @Override
    public List<OrdersDto> getAll() {
        return null;
    }

    @Override
    public Orders getById(Long theId) {
        Optional<Orders> foundOrder = orderDao.findById(theId);
        if (foundOrder.isPresent())
            return foundOrder.get();
        else throw new NotFoundDta("❌❌❌ Error, no order found ❌❌❌ ");
    }


    public List<OrdersDto> findOrdersOfSubService(int subServiceId) {
        List<Orders> orders = orderDao.findOrdersOfSubService(subServiceId);//findOrdersOfSubService
        return orders.stream().map(mapper::ordersToDto).collect(Collectors.toList());
    }

    public List<OrdersDto> findOrderOfCustomer(Customer customer) {
        List<Orders> orderOfCustomer = orderDao.findOrderOfCustomer(customer.getId());
        return orderOfCustomer.stream().map(mapper::ordersToDto).collect(Collectors.toList());
    }

    public void acceptedOffer(Orders orders, Offer offer) {
        orders.setState(OrderState.WAITING_FOR_EXPERT_TO_COMING_TO_YOUR_PLACE);
        offer.setStatus(OfferStatus.ACCEPTED);
        offerDao.updateOfferStatus(OfferStatus.REJECTED, offer.getId());
        orders.setExpert(offer.getExpert());
        orders.setAgreedPrice(offer.getProposedPrice());
        orderDao.save(orders);//این درسته که دوباره سیوش کنم؟؟؟؟؟؟؟؟؟؟؟
        offerDao.save(offer);
    }


}
