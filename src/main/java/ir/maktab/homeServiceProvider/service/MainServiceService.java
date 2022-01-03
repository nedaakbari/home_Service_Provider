package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.MainServiceDao;
import ir.maktab.homeServiceProvider.model.entity.service.MainService;

public class MainServiceService {
    MainServiceDao mainServiceDao;

    public void save(MainService mainService) {
        mainServiceDao.save(mainService);
    }


    public MainServiceDao getMainServiceDao() {
        return mainServiceDao;
    }

    public void setMainServiceDao(MainServiceDao mainServiceDao) {
        this.mainServiceDao = mainServiceDao;
    }
}
