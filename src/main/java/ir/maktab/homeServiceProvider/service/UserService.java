package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.util.requestFilter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserDao userDao;


    public void saveUser(User user) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            throw new RuntimeException("user with these use an pass is already exist");
        } else {
            userDao.save(user);
        }
    }

    public void deleteUser(User user) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            userDao.delete(user);
        } else {
            throw new RuntimeException("user doesn't exist");
        }
    }

    public User findUserById(int id) {
        return userDao.findUserById(id).get();
    }

 /*   public void update(User user) {
        userDao.update(user);
    }*/

    public Iterable<User> findAllUser() {
        return userDao.findAll();
    }

    public User findUserByUseAndPass(String username, String password) {
        Optional<User> user = userDao.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return user.get();
        } else
            throw new RuntimeException("no user found with these use and pass");
    }

    public boolean duplicateEmail(String email) {
        Optional<User> userByEmail = userDao.findUserByEmail(email);
        if (userByEmail.isPresent()) {
            return true;
        } else
            return false;
    }

   /* public void updateUser(User user) {
        userDao.update(user);
    }*/

  /*  public List<UserDto> findAllUsersByFilter(UserFilter userFilter) {
        return userDao.findUsersByFilter(userFilter);
    }*/

    //region setter & getter & constructor
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //endregion &
}
