package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.ExpertDao;
import ir.maktab.homeServiceProvider.model.dto.ExpertDto;
import ir.maktab.homeServiceProvider.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.util.requestFilter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExpertService {

    private ExpertDao expertDao;

    public void saveExpert(Expert expert) {
        Optional<Expert> foundExpert = expertDao.findByUseAndPass(expert.getUsername(), expert.getPassword());
        if (foundExpert.isPresent()) {
            throw new RuntimeException("this expert is already exist");
        } else {
            expertDao.save(expert);
        }

    }

    public void deleteExpert(Expert expert) {
        Optional<Expert> foundExpert = expertDao.findByUseAndPass(expert.getUsername(), expert.getPassword());
        if (foundExpert.isPresent()) {
            expertDao.delete(expert);
        } else {
            throw new RuntimeException("there is no expert with these info");
        }
    }

    public void updateExpert(Expert expert) {
        expertDao.update(expert);
    }

    public Expert findExpertByUseAndPass(String username, String password) {
        Optional<Expert> expert = expertDao.findByUseAndPass(username, password);
        if (expert.isPresent()) {
            return expert.get();
        } else
            throw new RuntimeException("no expert found with these use and pass");
    }

    public List<Expert> findAll() {
        return expertDao.findAll();
    }

    public List<ExpertDto> findAllUsersByFilter(UserFilter userFilter) {
        return expertDao.findAllExpertsByFilter(userFilter);
    }

    public Expert findByEmail(String email) {
        Optional<Expert> foundExpert = expertDao.findByEmail(email);
        if (foundExpert.isPresent())
            return foundExpert.get();
        else
            throw new RuntimeException("no expert found with this email");
    }


    //region setter & getter & constructor
    @Autowired
    public ExpertService(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }

    public ExpertDao getExpertDao() {
        return expertDao;
    }

    public void setExpertDao(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }
    //endregion
}
