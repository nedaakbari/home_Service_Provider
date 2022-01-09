package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.MainServiceDao;
import ir.maktab.homeServiceProvider.data.model.entity.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainServiceService {
    private final MainServiceDao mainServiceDao;

    public void saveMainService(MainService mainService) {
        mainServiceDao.save(mainService);
    }

    public void removeMainService(MainService mainService) {
        mainServiceDao.delete(mainService);
    }
  /*  public void saveMainService(MainService mainService) {
        Optional<MainService> foundMainService = mainServiceDao.findByName(mainService.getName());
        if (foundMainService.isPresent()) {
            throw new RuntimeException("❌❌❌ this main service is already exist ❌❌❌");
        } else {
            mainServiceDao.save(mainService);
        }
    }

    public void deleteMainService(MainService mainService) {
        Optional<MainService> foundMainService = mainServiceDao.findByName(mainService.getName());
        if (foundMainService.isPresent()) {
            mainServiceDao.delete(mainService);
        } else {
            throw new RuntimeException("❌❌❌ there is no  main service with this info ❌❌❌");
        }
    }

   *//* public void updateMainService(MainService mainService) {
        mainServiceDao.update(mainService);
    }*//*

*//*    public List<MainService> findAllMainService() {
        List<MainService> all = mainServiceDao.findAll();
        if (all.size() != 0) {
            return all;
        } else
            throw new RuntimeException("no mainService Exist");
    }*//*

    public MainService findByName(String name) {
        Optional<MainService> main = mainServiceDao.findByName(name);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new RuntimeException("there is no mainService With this name");
    }

    public MainService findById(int mainId) {
        Optional<MainService> main = mainServiceDao.findById(mainId);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new RuntimeException("there is no mainService With this id");
    }

    public Iterable<MainService> findAll() {
        return mainServiceDao.findAll();
    }*/

    //region getter & setter & constructor
    @Autowired
    public MainServiceService(MainServiceDao mainServiceDao) {
        this.mainServiceDao = mainServiceDao;
    }


    //endregion
}
