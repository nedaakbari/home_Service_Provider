package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Customer;
import ir.maktab.homeServiceProvider.model.entity.User;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
/**
 * author: neda akbari
 */
public class UserDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public int save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        if (id == 1) {
            return 1;
        }
        return -1;
    }


    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> users = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        session.close();
        return users;
    }

    public User findByUseAndPass(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From User U Where U.password = :password and  U.username=:username");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }


    public User findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From User U Where U.email = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }


}
