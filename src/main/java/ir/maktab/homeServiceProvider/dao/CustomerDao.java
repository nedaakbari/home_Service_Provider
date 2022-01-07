package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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

    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Customer ");
        List<Customer> customers = query.list();
        transaction.commit();
        session.close();
        return customers;
    }

    public Optional<Customer> findByUseAndPass(String userName,String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("From Customer C Where C.password = :password and  C.username=:username");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        Optional<Customer> customer = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return customer;
    }
    public Optional<Customer> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("FROM Customer C WHERE C.email=:email");
        query.setParameter("email", email);
        Optional<Customer> customer = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return customer;
    }

}
