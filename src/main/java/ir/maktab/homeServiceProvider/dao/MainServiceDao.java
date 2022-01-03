package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.service.MainService;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MainServiceDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(MainService mainService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(mainService);
        transaction.commit();
        session.close();
    }

    public void update(MainService mainService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(mainService);
        transaction.commit();
        session.close();
    }

    public void delete(MainService mainService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(mainService);
        transaction.commit();
        session.close();
    }

    public List<MainService> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from MainService ");
        List<MainService> mainService = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        session.close();
        return mainService;
    }

    public MainService findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<MainService> query = session.createQuery("From MainService M Where M.name=:name");
        query.setParameter("name", name);
        MainService mainService = query.uniqueResult();
        transaction.commit();
        session.close();
        return mainService;
    }




}
