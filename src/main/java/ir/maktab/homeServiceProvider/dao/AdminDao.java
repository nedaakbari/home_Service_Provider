package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Admin;
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
public class AdminDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(admin);
        transaction.commit();
        session.close();
    }

    public void update(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(admin);
        transaction.commit();
        session.close();
    }

    public void delete(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(admin);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Admin ");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }
    public Admin findByUseAndPass(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From Admin A Where A.passWord = :password and  A.userName=:username");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        Admin admin = (Admin) query.uniqueResult();
        transaction.commit();
        session.close();
        return admin;
    }

}
