package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.model.entity.Expert;
import ir.maktab.homeServiceProvider.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ExpertDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    /*  public int save(Expert expert) {
          Session session = sessionFactory.getCurrentSession();
          Transaction transaction = session.getTransaction();
          int id = (int) session.save(expert);
          transaction.commit();
          if (id == 1) {
              return 1;
          }
          return -1;
      }*/
    public void save(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
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

    public List<Expert> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Customer ");
        List<Expert> experts = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        session.close();
        return experts;
    }


}
