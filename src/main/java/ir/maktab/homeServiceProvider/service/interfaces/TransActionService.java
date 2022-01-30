package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.TransActions;
import ir.maktab.homeServiceProvider.dto.TransActionDto;

import java.util.List;

public interface TransActionService {
    void save(TransActions transActions);

    void delete(TransActions transActions);

    List<TransActionDto> getAll();

    TransActions getById(Long theId);
}
