package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.AdminDao;
import ir.maktab.homeServiceProvider.model.entity.Admin;

public class AdminService {
    private AdminDao adminDao;

    public void saveAdmin(Admin admin){
        adminDao.save(admin);
    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
}
