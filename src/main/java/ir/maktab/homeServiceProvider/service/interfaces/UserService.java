package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.Person.User;
import ir.maktab.homeServiceProvider.enums.Role;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.dto.UserFilterDto;

import java.util.List;

public interface UserService {
    void register(UserDto userDto);

    UserDto login(UserDto userDto);

    void delete(UserDto userDto);

    void updateUser(UserDto userDto);

    void updateCreditCart(double amount, UserDto userDto);

    void updatePassword(String newPassword, UserDto userDto);

    List<UserDto> getAll();

    UserDto getById(Integer theId);

    User getByEmail(String email);

    UserDto findUserByUseAndPass(String userName, String password);

    boolean isDuplicateEmail(String email);

    List<UserDto> findAllUsersByFilter(UserFilterDto userFilterDto);

    List<UserDto> findByPagination(int firstPage, int pageSize, String firstname, String lastname, String email, Role role);

}
