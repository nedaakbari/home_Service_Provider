package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.entity.TransActions;
import ir.maktab.homeServiceProvider.dto.OrdersDto;
import ir.maktab.homeServiceProvider.dto.TransActionDto;

import java.util.List;

public interface TransActionService {
    void save(TransActionDto transActionDto, OrdersDto ordersDto);

    void delete(TransActions transActions);

    List<TransActionDto> getAll();

    TransActions getById(Long theId);

    void paidForOrder(String ordersDto);
}
