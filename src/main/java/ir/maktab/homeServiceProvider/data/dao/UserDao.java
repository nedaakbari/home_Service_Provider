package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {

    Optional<User> findByUsernameAndPassword(String userName, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findById(int id);

/*    @Modifying
    @Query(value = "UPDATE User u set u.password =:password where u.id=:id")
    void updatePassword(@Param("password") String password, @Param("id") int id);*/

    static Specification<User> selectByCondition(String firstname, String lastname, String email, Role role) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firstname != null) {
                predicates.add(cb.equal(root.get("firstname"), firstname));
            }
            if (lastname != null) {
                predicates.add(cb.equal(root.get("lastname"), lastname));
            }
            if (email != null) {
                predicates.add(cb.equal(root.get("emailAddress"), email));
            }
            if (role != null) {
                predicates.add(cb.equal(root.get("role"), role));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}


