package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.TransActionDao;
import ir.maktab.homeServiceProvider.data.model.entity.TransActions;
import ir.maktab.homeServiceProvider.dto.TransActionDto;
import ir.maktab.homeServiceProvider.service.Services;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransActionService {
    void save(TransActions transActions);

    void delete(TransActions transActions);

    List<TransActionDto> getAll();

   TransActions getById(Long theId);


}
