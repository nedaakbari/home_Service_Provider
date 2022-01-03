package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.ExpertDao;
import ir.maktab.homeServiceProvider.model.entity.Expert;

public class ExpertService {

    private ExpertDao expertDao;

    public void saveExpert(Expert expert) {
        expertDao.save(expert);
    }

    public ExpertDao getExpertDao() {
        return expertDao;
    }

    public void setExpertDao(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }
}
