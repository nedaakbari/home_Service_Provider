package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrdersDto ordersToDto(Orders orders) {
        return OrdersDto.builder()
                .id(orders.getId())
                .address(orders.getAddress())
                .description(orders.getDescription())
                .workDay(orders.getWorkDay())
                .build();
    }

    public Orders ordersDtoToOrders(OrdersDto ordersDto) {
        return Orders.builder()
                .id(ordersDto.getId())
                .address(ordersDto.getAddress())
                .description(ordersDto.getDescription())
                .workDay(ordersDto.getWorkDay())
                .build();
    }
}
