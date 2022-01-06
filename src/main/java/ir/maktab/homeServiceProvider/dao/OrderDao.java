package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

//@Lazy
@Component
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
        transaction.commit();
        session.close();
        return orders;
    }

    public List<Orders> findOrder(int serviceId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders O join fetch O.subService S where S.id=:id and O.state='WAITING_FOR_EXPERT_SUGGESTION'");
        query.setParameter("id", serviceId);
        List<Orders> orders = query.list();
        transaction.commit();
        session.close();
        return orders;
    }

    public List<Orders> findOrderOfCustomer(int customerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders O join fetch O.customer C where C.id=:id");
        query.setParameter("id", customerId);
        List<Orders> orders = query.list();
        transaction.commit();
        session.close();
        return orders;
    }

    public Orders findOrderByID(int id) {
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
