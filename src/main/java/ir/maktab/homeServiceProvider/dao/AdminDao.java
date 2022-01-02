package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Admin;
import ir.maktab.homeServiceProvider.model.entity.User;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public int save(Admin admin) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        int id = (int) session.save(admin);
        transaction.commit();
        if (id == 1) {
            return 1;
        }
        return -1;
    }

    public void update(Admin admin) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        session.update(admin);
        transaction.commit();
    }

    public void delete(Admin admin) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        session.delete(admin);
        transaction.commit();
    }

    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        Query query = session.createQuery("from Admin ");
        List<User> users = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        return users;
    }

}
