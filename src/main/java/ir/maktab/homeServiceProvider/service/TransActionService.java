package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.TransActionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TransActionService {
    TransActionDao transActionDao;

  /*  public void save(TransAction transAction) {
        transActionDao.save(transAction);
    }

    public Iterable<TransAction> findAll() {
        return transActionDao.findAll();
    }*/

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
