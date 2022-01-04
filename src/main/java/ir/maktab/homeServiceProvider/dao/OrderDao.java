package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
/**
 * author: neda akbari
 */
public class OrderDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
         session.persist(order);
        transaction.commit();
        session.close();
    }

    public void update(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    public void delete(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
        session.close();
    }

    public List<Orders> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders ");
        List<Orders> orders = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        session.close();
        return orders;
    }

    public Orders findOrderByID(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders O where O.id=:id");
        query.setParameter("id", id);
        Orders order = (Orders) query.uniqueResult();
        transaction.commit();
        session.close();
        return order;
    }


}
