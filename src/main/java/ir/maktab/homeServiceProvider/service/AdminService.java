package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.AdminDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.mapper.AdminMapper;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper mapper;
    private final AdminDao adminDao;

    public void saveAdmin(Admin admin) {
        adminDao.save(admin);
    }

    public void removeAdmin(Admin admin) {
        adminDao.delete(admin);
    }

    public List<Admin> findAllAdmins() {
        List<Admin> list = new ArrayList<>();
        Iterable<Admin> admins = adminDao.findAll();
        admins.forEach(list::add);
        return list;
    }

    @Transactional
    public void UpdatePassword(String newPassword, int id) {
        adminDao.updatePassword(newPassword, id);
    }

    public Admin findAminByUseAndPass(String username, String password) {
        Optional<Admin> admin = adminDao.findByUserNameAndPassWord(username, password);
        if (admin.isPresent()) {
            return admin.get();
        } else
            throw new NotFoundDta("no admin found with these use and pass");
    }
    /*  public Admin findAminByUseAndPass(Admin admin) {
        Optional<Admin> found = adminDao.findByUserNameAndPassWord(admin.getUserName(), admin.getPassWord());
        return found.orElse(null);
    }*///todo which one is better


    public boolean isExist(String username, String password) {
        Optional<Admin> found = adminDao.findByUserNameAndPassWord(username, password);
        if (found != null)
            return true;
        else return false;
    }

    public Iterable<Admin> findAll() {
        return adminDao.findAll();
    }


   /* @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }*/

}
