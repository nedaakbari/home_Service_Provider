package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.AdminDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface AdminService {

    void save(Admin admin);

    void delete(Admin admin);

    public List<AdminDto> getAll();

    Admin getById(Integer theId);

    Admin findAminByUseAndPass(String username, String password);

    void UpdatePassword(String newPassword, int id);

    boolean isExist(String username, String password);


}
