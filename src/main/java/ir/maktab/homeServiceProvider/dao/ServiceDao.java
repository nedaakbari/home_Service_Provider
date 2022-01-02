package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Customer;
import ir.maktab.homeServiceProvider.model.entity.User;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subService);
        transaction.commit();
        session.close();
    }

    public void update(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(subService);
        transaction.commit();
        session.close();
    }

    public void delete(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(subService);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Customer ");
        List<User> users = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        session.close();
        return users;
    }

    public Customer findByUseAndPass(String userName,String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("From User U Where U.password = :password and  U.username=:username");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        Customer customer = query.uniqueResult();
        transaction.commit();
        session.close();
        return customer;
    }


}
