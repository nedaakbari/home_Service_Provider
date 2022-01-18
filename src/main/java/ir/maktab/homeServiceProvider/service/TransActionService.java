package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.TransActionDao;
import ir.maktab.homeServiceProvider.data.model.entity.TransActions;
import ir.maktab.homeServiceProvider.dto.TransActionDto;
import ir.maktab.homeServiceProvider.dto.mapperInterface.TransActionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransActionService implements Services<TransActions, TransActionDto,Long> {
    private final TransActionDao transActionDao;
   // private final TransActionMapper mapper;

    @Override
    public void save(TransActions transActions) {
        transActionDao.save(transActions);
    }

    @Override
    public void delete(TransActions transActions) {
        transActionDao.delete(transActions);
    }

    @Override
    public List<TransActionDto> getAll() {
        return null;
    }

    @Override
    public TransActions getById(Long theId) {
        return null;
    }


}
