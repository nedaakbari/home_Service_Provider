package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
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
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Integer> {


    //public int save(User user);
//    public void delete(User user);
//List<User> findAll() ;

   /* public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }*/

    //"From User U Where U.password = :password and  U.username=:username")
    Optional<User> findByUsernameAndPassword(String userName, String password);

    //"From User U Where U.email = :email"
    Optional<User> findUserByEmail(String email);

    //"From User U Where U.id = :id"
     Optional<User> findUserById(int id);


   /* public List<UserDto> findUsersByFilter(UserFilter filter) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class, "u");
        if (filter.getRole() != null) {
            SimpleExpression filterByRole = Restrictions.eq("u.role", filter.getRole());
            criteria.add(filterByRole);
        }
        if (filter.getEmail() != null) {
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
    }*/

}
