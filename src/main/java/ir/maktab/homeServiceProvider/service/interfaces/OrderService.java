package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.entity.Orders;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.dto.CustomerDto;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.OrdersDto;

import java.util.List;

public interface OrderService {

    void save(OrdersDto ordersDto, CustomerDto customerDto, AddressDto addressDto, String subCategoryDto);

    void delete(OrdersDto ordersDto);

    void update(OrdersDto ordersDto);

    List<OrdersDto> getAll();

    Orders getById(Long theId);

    List<OrdersDto> findOrdersOfSubService(int subServiceId);

    List<OrdersDto> findOrderOfCustomer(CustomerDto customerDto);

    Orders findByUUID(String uuid);

    List<OrdersDto> findOrdersForExpert(ExpertDto expertDto);

    void updateState(Orders orders);

}
