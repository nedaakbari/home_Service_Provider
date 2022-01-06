package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.entity.TransAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransActionDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(TransAction transAction) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(transAction);
        transaction.commit();
        session.close();
    }


    public List<TransAction> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from TransAction ");
        List<TransAction> transActions = query.list();
        transaction.commit();
        session.close();
        return transActions;
    }
}
