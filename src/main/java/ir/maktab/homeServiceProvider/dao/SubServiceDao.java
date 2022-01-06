package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubServiceDao {
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


    public List<SubService> findSubservienceFromMainService(int mainServiceId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<SubService> query = session.createQuery("From SubService S JOIN fetch S.main where S.main.id=:id");
        query.setParameter("id", mainServiceId);
        List<SubService> services = query.list();
        transaction.commit();
        session.close();
        return services;
    }

    public Optional<SubService> findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<SubService> query = session.createQuery("From SubService S where S.name=:name");
        query.setParameter("name", name);
        Optional<SubService> subService = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return subService;
    }
    public Optional<SubService> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<SubService> query = session.createQuery("From SubService S where S.id=:id");
        query.setParameter("id", id);
        Optional<SubService> subService = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return subService;
    }

    public List<SubService> findSubserivceOfExpert(int expertId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("From SubService S JOIN fetch S.experts E where E.id=:id");
        query.setParameter("id", expertId);
        List<SubService> services = query.list();
        transaction.commit();
        session.close();
        return services;
    }

    public List<SubService> findByNameCriteria(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(SubService.class, "s");
        criteria.setFetchMode("experts", FetchMode.EAGER);
        criteria.add(Restrictions.eq("s.name", name));
        List<SubService> subServiceList = criteria.list();
        transaction.commit();
        session.close();
        return subServiceList;
    }


}
