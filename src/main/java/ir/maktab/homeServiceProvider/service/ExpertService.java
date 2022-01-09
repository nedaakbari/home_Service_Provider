package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.ExpertDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpertService {

    private final ExpertDao expertDao;

    public void saveExpert(Expert expert) {
        expertDao.save(expert);
    }

    public void removeExpert(Expert expert) {
        expertDao.delete(expert);
    }


  /*  public Expert findExpertByUseAndPass(String username, String password) {
        Optional<Expert> expert = expertDao.findByUsernameAndPassword(username, password);
        if (expert.isPresent()) {
            return expert.get();
        } else
            throw new RuntimeException("no expert found with these use and pass");
    }*/


    //region setter & getter & constructor
    @Autowired
    public ExpertService(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }

    //endregion
}
