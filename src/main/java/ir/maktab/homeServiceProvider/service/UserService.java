package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.UserDao;
import ir.maktab.homeServiceProvider.model.dto.UserDto;
import ir.maktab.homeServiceProvider.model.entity.Person.User;
import ir.maktab.homeServiceProvider.util.filter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private UserDao userDao;


    public void saveUser(User user) {
        Optional<User> foundUser = userDao.findByUseAndPass(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            throw new RuntimeException("user with these use an pass is already exist");
        } else {
            userDao.save(user);
        }
    }

    public void deleteUser(User user) {
        Optional<User> foundUser = userDao.findByUseAndPass(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            userDao.delete(user);
        } else {
            throw new RuntimeException("user doesn't exist");
        }
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List<User> findAllUser() {
        return userDao.findAll();
    }

    public User findUserByUseAndPass(String username, String password) {
        Optional<User> user = userDao.findByUseAndPass(username, password);
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

    public void updateUser(User user) {
        userDao.update(user);
/*        int update =userDao.update(user);///???????????چجوری چک کنم ابدیت شده یا نه؟
        if (update==1)
            System.out.println("your pass successfully changed");
        else
            System.out.println("your request not response, try again");*/
    }

    public List<UserDto> findAllUsersByFilter(UserFilter userFilter) {
        return userDao.findUsersByFilter(userFilter);
    }

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
