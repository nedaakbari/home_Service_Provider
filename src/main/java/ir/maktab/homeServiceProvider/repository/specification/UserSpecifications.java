package ir.maktab.homeServiceProvider.repository.specification;

import ir.maktab.homeServiceProvider.entity.Person.User;
import ir.maktab.homeServiceProvider.enums.Role;
import ir.maktab.homeServiceProvider.dto.UserFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface UserSpecifications {//برای فیلتر کردنه
    static Specification<User> filterUsers(UserFilterDto dto) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (dto.getFirstName() != null)
                predicates.add(cb.equal(root.get("firstName"), dto.getFirstName()));
            if (dto.getLastName()!= null)
                predicates.add(cb.equal(root.get("lastName"), dto.getLastName()));
            if (dto .getEmail()!= null)
                predicates.add(cb.equal(root.get("email"), dto .getEmail()));
            if (dto.getRole() != null)
                predicates.add(cb.equal(root.get("role"), dto.getRole()));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

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
