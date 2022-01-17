package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.dto.OrdersDto;

public class OrderMapper {
    public OrdersDto ordersDto(Orders orders) {
        return OrdersDto.builder()
                .id(orders.getId())
                .address(orders.getAddress())
                .description(orders.getDescription())
                .workDay(orders.getWorkDay())
                .build();
    }
}
