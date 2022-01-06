package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.SubServiceDao;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class SubServiceService {
    SubServiceDao subServiceDao;


    public void saveSubService(SubService subService) {
        Optional<SubService> foundSubService = subServiceDao.findByName(subService.getName());
        if (subService.getMain() == null) {
            throw new RuntimeException("❌❌❌ field of mainService can't be empty ❌❌❌");
        }
        if (foundSubService.isPresent()) {
            throw new RuntimeException("❌❌❌ this subService is already exist ❌❌❌");
        } else {
            subServiceDao.save(subService);
        }
    }

    public void deleteSubService(SubService subService) {
        Optional<SubService> foundSubService = subServiceDao.findByName(subService.getName());
        if (foundSubService.isPresent()) {
            subServiceDao.delete(subService);
        } else {
            throw new RuntimeException("❌❌❌ this subService is not exist ❌❌❌");
        }
    }

    public void updateSubService(SubService subService) {
        subServiceDao.update(subService);
    }

    public List<SubService> findAll() {
        return subServiceDao.findAll();
    }

    public SubService findByName(String name) {
        Optional<SubService> found = subServiceDao.findByName(name);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new RuntimeException("❌❌❌ this subService is not exist ❌❌❌");
        }
    }

    public List<SubService> findSubservienceFromMainService(int id) {
        return subServiceDao.findSubservienceFromMainService(id);
    }

    public SubService findById(int id) {
        Optional<SubService> foundService = subServiceDao.findById(id);
        if (foundService.isPresent()) {
           return foundService.get();
        } else {
            throw new RuntimeException("❌❌❌ this subService is not exist ❌❌❌");
        }
    }

    public List<SubService> findSubserivceOfExpert(int expertId){
        return subServiceDao.findSubserivceOfExpert(expertId);
    }

    //region setter & getter & constructor
    @Autowired
    public SubServiceService(SubServiceDao serviceDao) {
        this.subServiceDao = serviceDao;
    }

    public SubServiceDao getSubServiceDao() {
        return subServiceDao;
    }

    public void setSubServiceDao(SubServiceDao subServiceDao) {
        this.subServiceDao = subServiceDao;
    }
    //endregion
}
