package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.dao.CustomerDao;
import ir.maktab.homeServiceProvider.dao.ExpertDao;
import ir.maktab.homeServiceProvider.dao.UserDao;

import java.util.List;

public class UserService {
    private UserDao userDao;
    private CustomerDao customerDao;
    private ExpertDao expertDao;

    public void saveUser(User user) {
        userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List<User> findAllUser() {
        return userDao.findAll();
    }

    public User findUserByUseAndPass(String username, String password) {
        return userDao.findByUseAndPass(username, password);
    }

    public boolean duplicatePassword(String pass) {
        List<User> users = userDao.findAll();
        if (users.size()==0)
            return false;
        else if (users.stream().anyMatch(user -> user.getPassword().equals(pass)))
            return true;
        return false;
    }

    public boolean duplicateEmail(String email) {
        List<User> users = userDao.findAll();
        if (users.size()==0)
            return false;
        else if (users.stream().anyMatch(user -> user.getEmail().equals(email)))
            return true;
        return false;
    }

    public void updateUser(User user){
        userDao.update(user);
    }

    //region setter & getter
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public ExpertDao getExpertDao() {
        return expertDao;
    }

    public void setExpertDao(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }
    //endregion
}
