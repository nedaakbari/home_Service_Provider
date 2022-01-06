package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.dto.OrdersDto;
import ir.maktab.homeServiceProvider.model.entity.Orders;
import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public List<Orders> findOrdersOfSubService(int serviceId) {
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
        Query query = session.createQuery("from Orders O join fetch O.customer C where C.id=:id ");//todo and O.state NOT IN ('PAID')
        query.setParameter("id", customerId);
        List<Orders> orders = query.list();
        transaction.commit();
        session.close();
        return orders;
    }

    public Optional<Orders> findOrderByID(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Orders> query = session.createQuery("from Orders O where O.id=:id");
        query.setParameter("id", id);
        Optional<Orders> order = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return order;
    }

}
