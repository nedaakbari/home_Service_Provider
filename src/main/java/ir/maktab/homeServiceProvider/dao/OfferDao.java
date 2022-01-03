package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OfferDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Offer offer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(offer);
        transaction.commit();
        session.close();
    }

    public void update(Offer offer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(offer);
        transaction.commit();
        session.close();
    }

    public void delete(Offer offer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(offer);
        transaction.commit();
        session.close();
    }

    public List<Offer> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Offer ");
        List<Offer> offers = query.list();
        transaction.commit();
        session.close();
        return offers;
    }


}
