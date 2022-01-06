package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.AdminDao;
import ir.maktab.homeServiceProvider.model.entity.Person.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Service
@Component
public class AdminService {

    private AdminDao adminDao;

    public void saveAdmin(Admin admin) {
        adminDao.save(admin);
    }

    public void updateAdmin(Admin admin) {
        adminDao.update(admin);
    }

    public void deleteAdmin(Admin admin) {
        adminDao.delete(admin);
    }

    public Admin findAminByUseAndPass(String username, String password) {
        Optional<Admin> admin = adminDao.findByUseAndPass(username, password);
        if (admin.isPresent()) {
            return admin.get();
        } else
            throw new RuntimeException("no admin found with these use and pass");
    }

    public List<Admin> findAll() {
        return adminDao.findAll();
    }

   /* public Admin findAminByUseAndPass(Admin admin) {
        Optional<Admin> found = adminDao.findByUseAndPass(admin.getUserName(), admin.getPassWord());
        return found.orElse(null);
    }*/


    //region setter & getter & constructor
    public AdminDao getAdminDao() {
        return adminDao;
    }

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    //endregion
}
