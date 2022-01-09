package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.service.MainService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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

    public Optional<MainService> findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<MainService> query = session.createQuery("From MainService M Where M.name=:name");
        query.setParameter("name", name);
        Optional<MainService> mainService = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return mainService;
    }

    public Optional<MainService> findById(int mainId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<MainService> query = session.createQuery("From MainService M Where M.id=:id");
        query.setParameter("id", mainId);
        Optional<MainService> mainService = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return mainService;
    }


}
