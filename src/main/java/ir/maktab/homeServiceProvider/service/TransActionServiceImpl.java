package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.TransActionDao;
import ir.maktab.homeServiceProvider.data.model.entity.TransActions;
import ir.maktab.homeServiceProvider.dto.TransActionDto;
import ir.maktab.homeServiceProvider.service.interfaces.TransActionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransActionServiceImpl /*implements TransActionService*/ {
    private  ModelMapper mapper =new ModelMapper();
    private final TransActionDao transActionDao;

    public void save(TransActions transActions) {
        transActionDao.save(transActions);
    }

    public void delete(TransActions transActions) {
        transActionDao.delete(transActions);
    }

    public List<TransActionDto> getAll() {
        return transActionDao.findAll().stream()
                .map(transActions -> mapper.map(transActions,TransActionDto.class)).collect(Collectors.toList());
    }

    public TransActions getById(Long theId) {
        return null;
    }


}
