package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.AdminDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminDao adminDao;

    public void saveAdmin(Admin admin) {
        adminDao.save(admin);
    }
/*

 */
/*   public void updateAdmin(Admin admin) {
        adminDao.update(admin);
    }*//*


    public void deleteAdmin(Admin admin) {
        adminDao.delete(admin);
    }

*/
/*    public Admin findAminByUseAndPass(String username, String password) {
        Optional<Admin> admin = adminDao.findByUserNameAndPassWord(username, password);
        if (admin.isPresent()) {
            return admin.get();
        } else
            throw new RuntimeException("no admin found with these use and pass");
    }*//*


  */
/*  public Admin findAminByUseAndPass(Admin admin) {
        Optional<Admin> found = adminDao.findByUserNameAndPassWord(admin.getUserName(), admin.getPassWord());
        return found.orElse(null);
    }*//*
//todo which one is better


    public boolean isExist(String username, String password) {
        Optional<Admin> found = adminDao.findByUserNameAndPassWord(username, password);
        if (found != null)
            return true;
        else return false;
    }

    public Iterable<Admin> findAll() {
        return adminDao.findAll();
    }
*/

    //region setter & getter & constructor

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    //endregion
}
