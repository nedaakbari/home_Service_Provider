package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.TransActionDao;
import ir.maktab.homeServiceProvider.model.entity.TransAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransActionService {
    TransActionDao transActionDao;

    public void save(TransAction transAction) {
        transActionDao.save(transAction);
    }

    public List<TransAction> findAll() {
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
