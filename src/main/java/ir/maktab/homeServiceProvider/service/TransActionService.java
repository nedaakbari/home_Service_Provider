package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.TransActionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransActionService {
    private final TransActionDao transActionDao;

  /*  public void save(TransAction transAction) {
        transActionDao.save(transAction);
    }

    public Iterable<TransAction> findAll() {
        return transActionDao.findAll();
    }*/

}
