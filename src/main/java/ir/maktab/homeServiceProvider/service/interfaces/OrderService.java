package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.OfferDao;
import ir.maktab.homeServiceProvider.data.dao.OrderDao;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface OrderService {

    void save(Orders orders);

    void delete(Orders orders);

    List<OrdersDto> getAll();

    Orders getById(Long theId);

    public List<OrdersDto> findOrdersOfSubService(int subServiceId);

    public List<OrdersDto> findOrderOfCustomer(Customer customer);

    public void acceptedOffer(Orders orders, Offer offer);

}
