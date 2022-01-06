package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.dto.OrdersDto;
import ir.maktab.homeServiceProvider.model.entity.Offer;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Lazy
@Component
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

    public List<Offer> findAllOfferOfAnOrder(int OrderId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Offer O join fetch O.orders S where S.id=:id ");//todo چک کن فقط اونایی نشونش بده که باید انتظار باشن and s.state=''
        query.setParameter("id", OrderId);
        List<Offer> offers = query.list();
        transaction.commit();
        session.close();
        return offers;
    }



    public Offer findOfferById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From Offer o where o.id=:id");
        query.setParameter("id", id);
        Offer offer = (Offer) query.uniqueResult();
        transaction.commit();
        session.close();
        return offer;
    }

}
