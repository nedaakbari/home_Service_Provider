package ir.maktab.homeServiceProvider.service.interfaces;


import ir.maktab.homeServiceProvider.data.entity.Orders;
import ir.maktab.homeServiceProvider.data.enums.OrderState;
import ir.maktab.homeServiceProvider.dto.*;

import java.util.List;

public interface OrderService {

    void save(OrdersDto ordersDto, CustomerDto customerDto, AddressDto addressDto, String subCategoryDto);

    void delete(OrdersDto ordersDto);

    void updateState(OrdersDto ordersDto, OrderState state);

    List<OrdersDto> getAll();

    Orders getById(Long theId);

    List<OrdersDto> findOrdersOfSubService(int subServiceId);

    List<OrdersDto> findOrdersOfSubServices(String subServiceTitle);

    List<OrdersDto> findOrderOfCustomer(CustomerDto customerDto);

    Orders findByUUID(String uuid);

    OrdersDto findOrderByCodeNumber(String CodeNumber);

    List<OrdersDto> findOrdersForExpert(ExpertDto expertDto);

    void placeScore(String orderCodeNumber, String comment, double score);

}
