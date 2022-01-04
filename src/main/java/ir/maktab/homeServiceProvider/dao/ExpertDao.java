package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Expert;
import ir.maktab.homeServiceProvider.model.entity.User;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import ir.maktab.homeServiceProvider.util.ImageWrapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * author: neda akbari
 */
public class ExpertDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();


    public void save(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ImageWrapper.saveImage(expert.getName()+".jpg",expert);
        session.save(expert);
        transaction.commit();
        session.close();
    }

    public void update(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }

    public void delete(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(expert);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Expert ");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }

}
