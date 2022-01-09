package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.UserDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public void saveUser(User user) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("❌❌❌ Error,user  with these use an pass is already exist ❌❌❌");
        } else {
            userDao.save(user);
        }
    }

    public void removeUser(User user) {
        userDao.delete(user);
    }

    @Transactional
    public void updatePassword(String newPassword, int id) {
        userDao.updatePassword(newPassword, id);
    }

    public User findUserByUseAndPass(String userName, String password) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(userName, password);
        if (foundUser.isPresent())
            return foundUser.get();
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }

    public User findUserById(int id) {
        Optional<User> foundUser = userDao.findById(id);
        if (foundUser.isPresent())
            return foundUser.get();
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }


    public void deleteUser(User user) {
        userDao.delete(user);
    }


    public Iterable<User> findAllUser() {
        return userDao.findAll();
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

    //region setter & getter & constructor
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    //endregion &
}
