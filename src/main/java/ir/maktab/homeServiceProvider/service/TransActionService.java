package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.model.entity.TransAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransActionService {
    TransActionDao transActionDao;

    public void save(TransAction transAction) {
        transActionDao.save(transAction);
    }

    public Iterable<TransAction> findAll() {
        return transActionDao.findAll();
    }

    //region getter & setter & constructor
    @Autowired
    public TransActionService(TransActionDao transActionDao) {
        this.transActionDao = transActionDao;
    }

    public TransActionDao getTransActionDao() {
        return transActionDao;
    }

    public void setTransActionDao(TransActionDao transActionDao) {
        this.transActionDao = transActionDao;
    }
    //endregion
}
