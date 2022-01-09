package ir.maktab.homeServiceProvider.data.dao;


import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.util.requestFilter.UserFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdminDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(admin);
        transaction.commit();
        session.close();
    }

    public void update(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(admin);
        transaction.commit();
        session.close();
    }

    public void delete(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(admin);
        transaction.commit();
        session.close();
    }

    public List<Admin> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Admin ");
        List<Admin> admins = query.list();
        transaction.commit();
        session.close();
        return admins;
    }

    public Optional<Admin> findByUseAndPass(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Admin> query = session.createQuery("From Admin A Where A.passWord = :password and  A.userName=:username");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        Optional<Admin> admin = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return admin;
    }

    public List<AdminDto> findAdminsByFilter(UserFilter filter) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Admin.class, "a");

        if (filter.getEmail() != null) {
            SimpleExpression filterByEmail = Restrictions.eq("a.email", filter.getEmail());
            criteria.add(filterByEmail);
        }

        if (filter.getName() != null) {
            SimpleExpression filterByName = Restrictions.eq("a.name", filter.getName());
            criteria.add(filterByName);
        }
        if (filter.getLastName() != null) {
            SimpleExpression filterByFamily = Restrictions.eq("a.lastName", filter.getLastName());
            criteria.add(filterByFamily);
        }

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("a.name").as("name"))
                .add(Projections.property("a.lastName").as("lastName"))
                .add(Projections.property("a.phoneNumber").as("phoneNumber"))
                .add(Projections.property("a.email").as("email"))
                .add(Projections.property("a.id").as("id")));

        criteria.setResultTransformer(Transformers.aliasToBean(AdminDto.class));
        List<AdminDto> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }


}
