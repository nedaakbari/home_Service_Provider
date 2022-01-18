package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.UserDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl /*implements UserService*/ {
    private ModelMapper mapper = new ModelMapper();
    private final UserDao userDao;

    public void save(User user) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("❌❌❌ Error,user with these use an pass is already exist ❌❌❌");
        } else {
            userDao.save(user);
        }
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public List<UserDto> getAll() {
        return userDao.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    public User getById(Integer theId) {
        Optional<User> foundUser = userDao.findById(theId);
        if (foundUser.isPresent())
            return foundUser.get();
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }

  /*  @Transactional
    public void updatePassword(String newPassword, int id) {
        userDao.updatePassword(newPassword, id);
    }*/

    public void updatePassword(String newPassword, User user) {
        user.setPassword(newPassword);
        userDao.save(user);
    }

    public void updateCreditCart(double amount, User user) {
        user.setCreditCart(amount);
        userDao.save(user);
    }

    public void updateUser(User user) {
        userDao.save(user);
    }

 /*   @Transactional
    public void updateCreditCart(Double amount,int id) {
        userDao.updateCreditCart(amount,id);
    }*/ //todo which one is better?

    public User findUserByUseAndPass(String userName, String password) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(userName, password);
        if (foundUser.isPresent())
            return foundUser.get();
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }

    public boolean isDuplicateEmail(String email) {
        Optional<User> userByEmail = userDao.findByEmail(email);
        if (userByEmail.isPresent()) {
            return true;
        } else
            return false;
    }

/*  public List<UserDto> findAllUsersByFilter(UserFilter userFilter) {
        return userDao.findUsersByFilter(userFilter);
    }*/


}
