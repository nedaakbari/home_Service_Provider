package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.ExpertDao;

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
