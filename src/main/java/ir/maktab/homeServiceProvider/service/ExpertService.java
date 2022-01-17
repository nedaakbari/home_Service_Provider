package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.ExpertDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.dto.mapper.ExpertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertMapper mapper;
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


}
