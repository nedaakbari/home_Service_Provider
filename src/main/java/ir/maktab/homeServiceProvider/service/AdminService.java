package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.AdminDao;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.dto.mapper.AdminMapper;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements Services<Admin, AdminDto, Integer> {
    private final AdminMapper mapper;
    private final AdminDao adminDao;

    @Override
    public void save(Admin admin) {
        adminDao.save(admin);
    }

    @Override
    public void delete(Admin admin) {
        adminDao.delete(admin);
    }

    @Override
    public List<AdminDto> getAll() {
        List<Admin> allAdmin = adminDao.findAll();
        return allAdmin.stream().map(mapper::adminToDto).collect(Collectors.toList());
    }

    @Override
    public Admin getById(Integer theId) {
        Optional<Admin> found = adminDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no admin found ");
    }

    public Admin findAminByUseAndPass(String username, String password) {
        Optional<Admin> admin = adminDao.findByUserNameAndPassWord(username, password);
        if (admin.isPresent()) {
            return admin.get();
        } else
            throw new NotFoundDta("no admin found with these use and pass");
    }

    @Transactional
    public void UpdatePassword(String newPassword, int id) {
        adminDao.updatePassword(newPassword, id);
    }


    public boolean isExist(String username, String password) {
        Optional<Admin> found = adminDao.findByUserNameAndPassWord(username, password);
        if (found != null)
            return true;
        else return false;
    }

            /*  public Admin findAminByUseAndPass(Admin admin) {
        Optional<Admin> found = adminDao.findByUserNameAndPassWord(admin.getUserName(), admin.getPassWord());
        return found.orElse(null);
    }*///todo which one is better

}
