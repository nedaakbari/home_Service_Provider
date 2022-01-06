package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
/**
 * author: neda akbari
 */
public class ServiceDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subService);
        transaction.commit();
        session.close();
    }

    public void update(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(subService);
        transaction.commit();
        session.close();
    }

    public void delete(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(subService);
        transaction.commit();
        session.close();
    }

    public List<SubService> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubService ");
        List<SubService> services = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        session.close();
        return services;
    }

    public List<SubService> findByCategory() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<SubService> query = session.createQuery("From SubService S join fetch S.main ");

        List<SubService> services = query.list();
        transaction.commit();
        session.close();
        return services;
    }

    public SubService findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From SubService S where S.name=:name");
        query.setParameter("name", name);
        SubService result = (SubService) query.uniqueResult();
        transaction.commit();
        session.close();
        return result;
    }


    public void deleteExpertByName(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" delete From SubService S where S.experts.id=:id ");
        query.setParameter("id", expert.getId());
        transaction.commit();
        session.close();
    }//??????????????????????




}
