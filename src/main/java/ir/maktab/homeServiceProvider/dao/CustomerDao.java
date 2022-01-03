package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Customer;
import ir.maktab.homeServiceProvider.model.entity.User;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    public void delete(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
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
