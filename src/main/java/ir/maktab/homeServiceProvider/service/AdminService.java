package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.AdminDao;

public class AdminService {
    private AdminDao adminDao;

    public void saveAdmin(Admin admin){
        adminDao.save(admin);
    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public Admin findAminByUseAndPass(String username, String password) {
        return adminDao.findByUseAndPass(username, password);
    }
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
}
