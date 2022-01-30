package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.Orders;
import ir.maktab.homeServiceProvider.entity.Person.Customer;
import ir.maktab.homeServiceProvider.dto.OrdersDto;

import java.util.List;

public interface OrderService {

    void save(OrdersDto ordersDto);

    void delete(OrdersDto ordersDto);

    void update(OrdersDto ordersDto);

    List<OrdersDto> getAll();

    Orders getById(Long theId);

    List<OrdersDto> findOrdersOfSubService(int subServiceId);

    List<OrdersDto> findOrderOfCustomer(Customer customer);

    Orders findByUUID(String uuid);


}
