package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AdminDto;

import java.util.List;


public interface AdminService {

    void save(AdminDto adminDto);

    void delete(AdminDto adminDto);

    List<AdminDto> getAll();

    AdminDto getById(Integer theId);

    AdminDto findAminByUseAndPass(String username, String password);

    void updatePassword(String oldPassword, String newPassword, AdminDto adminDto);

    boolean isExist(String username, String password);

    Admin findAminByEmail(String email);

}
