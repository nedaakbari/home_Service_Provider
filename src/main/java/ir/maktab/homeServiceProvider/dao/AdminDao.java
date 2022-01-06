package ir.maktab.homeServiceProvider.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.entity.Person.Admin;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * author: neda akbari
 */
@Component
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

    public List<Admin> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Admin ");
        List<Admin> admins = query.list();
        transaction.commit();
        session.close();
        return admins;
    }

    public Optional<Admin> findByUseAndPass(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Admin> query = session.createQuery("From Admin A Where A.passWord = :password and  A.userName=:username");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        Optional<Admin> admin = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return admin;
    }

}
