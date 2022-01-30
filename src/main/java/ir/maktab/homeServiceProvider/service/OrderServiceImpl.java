package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.entity.Orders;
import ir.maktab.homeServiceProvider.entity.Person.Customer;
import ir.maktab.homeServiceProvider.enums.OrderState;
import ir.maktab.homeServiceProvider.repository.OrderRepository;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ModelMapper mapper;
    private final OrderRepository orderDao;

    @Override
    public void save(OrdersDto ordersDto) {
        Orders orders = mapper.map(ordersDto, Orders.class);
        orders.setState(OrderState.WAITING_FOR_EXPERT_SUGGESTION);
        orderDao.save(orders);
    }

    @Override
    public void update(OrdersDto ordersDto) {
        Optional<Orders> orders = orderDao.findByCodeNumber(ordersDto.getCodeNumber());
        orderDao.save(orders.get());
    }

    public void updateState(Orders orders) {
        orderDao.save(orders);
    }

    @Override
    public void delete(OrdersDto ordersDto) {
        Optional<Orders> orders = orderDao.findByCodeNumber(ordersDto.getCodeNumber());
        orderDao.delete(orders.get());
    }

    @Override
    public List<OrdersDto> getAll() {
        return orderDao.findAll().stream()
                .map(orders -> mapper.map(orders, OrdersDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Orders getById(Long theId) {
        Optional<Orders> foundOrder = orderDao.findById(theId);
        return foundOrder.orElseThrow(() -> new NotFoundDta("❌❌❌ Error, no order found ❌❌❌ "));
    }

    @Override
    public Orders findByUUID(String uuid) {
        Optional<Orders> foundOrder = orderDao.findByCodeNumber(uuid);
        return foundOrder.orElseThrow(() -> new NotFoundDta("❌❌❌ Error, no order found ❌❌❌ "));

    }

    @Override
    public List<OrdersDto> findOrdersOfSubService(int subServiceId) {
        List<Orders> orders = orderDao.findOrdersOfSubService(subServiceId);//findOrdersOfSubService
        return orders.stream().map(item -> mapper.map(item, OrdersDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrdersDto> findOrderOfCustomer(Customer customer) {
        List<Orders> orderOfCustomer = orderDao.findOrderOfCustomer(customer.getId());
        return orderOfCustomer.stream().map(item -> mapper.map(item, OrdersDto.class)).collect(Collectors.toList());
    }

}
