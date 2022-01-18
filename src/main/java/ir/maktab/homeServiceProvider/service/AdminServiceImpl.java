package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.AdminDao;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AddressDto;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.AddressService;
import ir.maktab.homeServiceProvider.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ModelMapper mapper;
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
        return adminDao.findAll().stream()
                .map(admin -> mapper.map(admin, AdminDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Admin getById(Integer theId) {
        Optional<Admin> found = adminDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no admin found ");
    }

    @Override
    public Admin findAminByUseAndPass(String username, String password) {
        Optional<Admin> admin = adminDao.findByUserNameAndPassWord(username, password);
        if (admin.isPresent()) {
            return admin.get();
        } else
            throw new NotFoundDta("no admin found with these use and pass");
    }

    @Override
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
