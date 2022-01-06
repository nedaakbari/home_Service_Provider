package ir.maktab.homeServiceProvider.dao;

import ir.maktab.homeServiceProvider.config.HibernateUtil;
import ir.maktab.homeServiceProvider.model.dto.UserDto;
import ir.maktab.homeServiceProvider.model.entity.Person.User;
import ir.maktab.homeServiceProvider.util.filter.UserFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public int save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        if (id == 1) {
            return 1;
        }
        return -1;
    }


    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> users = query.list();
        //users = session.createQuery("from User ").list();
        transaction.commit();
        session.close();
        return users;
    }

    public Optional<User> findByUseAndPass(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("From User U Where U.password = :password and  U.username=:username");
        query.setParameter("username", userName);
        query.setParameter("password", password);
        Optional<User> user = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return user;
    }


    public Optional<User> findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("From User U Where U.email = :email");
        query.setParameter("email", email);
        Optional<User> user = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return user;
    }

    public List<UserDto> findUsersByFilter(UserFilter filter) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class, "u");
        if (filter.getRole() != null) {
            SimpleExpression filterByRole = Restrictions.eq("u.role", filter.getRole());
            criteria.add(filterByRole);
        }
        if (filter.getEmail()!= null) {
            SimpleExpression filterByEmail = Restrictions.eq("u.email", filter.getEmail());
            criteria.add(filterByEmail);
        }
        if (filter.getStatus() != null) {
            SimpleExpression filterByStatus = Restrictions.eq("u.status", filter.getStatus());
            criteria.add(filterByStatus);
        }
        if (filter.getName() != null) {
            SimpleExpression filterByName = Restrictions.eq("u.name", filter.getName());
            criteria.add(filterByName);
        }
        if (filter.getLastName() != null) {
            SimpleExpression filterByFamily = Restrictions.eq("u.lastName", filter.getLastName());
            criteria.add(filterByFamily);
        }

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("u.registerDate").as("registerDate"))
                .add(Projections.property("u.role").as("role"))
                .add(Projections.property("u.name").as("name"))
                .add(Projections.property("u.lastName").as("lastName"))
                .add(Projections.property("u.role").as("role"))
                .add(Projections.property("u.phoneNumber").as("phoneNumber"))
                .add(Projections.property("u.email").as("email"))
                .add(Projections.property("u.status").as("status"))
                .add(Projections.property("u.id").as("id")));

        criteria.setResultTransformer(Transformers.aliasToBean(UserDto.class));
        List<UserDto> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }

}
