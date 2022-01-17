package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    //"From User U Where U.password = :password and  U.username=:username")
    Optional<User> findByUsernameAndPassword(String userName, String password);

    //"From User U Where U.email = :email"
    Optional<User> findByEmail(String email);

    //"From User U Where U.id = :id"
    Optional<User> findById(int id);

    @Modifying
    @Query(value = "UPDATE User u set u.password =:password where u.id=:id")
    void updatePassword(@Param("password") String password, @Param("id") int id);

}

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
