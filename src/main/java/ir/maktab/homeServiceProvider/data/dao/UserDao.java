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
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsernameAndPassword(String userName, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findById(int id);

    /*default*///?????چرا
    static Specification<User> selectByFilter(String firstName, String lastName, String email, Role role) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null)
                predicates.add(cb.equal(root.get("firstName"), firstName));
            if (lastName != null)
                predicates.add(cb.equal(root.get("lastName"), lastName));
            if (email != null)
                predicates.add(cb.equal(root.get("email"), email));
            if (role != null)
                predicates.add(cb.equal(root.get("role"), role));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}


