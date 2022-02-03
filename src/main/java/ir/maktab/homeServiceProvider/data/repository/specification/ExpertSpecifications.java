package ir.maktab.homeServiceProvider.data.repository.specification;

import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import ir.maktab.homeServiceProvider.dto.ExpertFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface ExpertSpecifications {

    static Specification<Expert> filterExpert(ExpertFilterDto dto) {
        return (root, cq, cb) -> {
            CriteriaQuery<Expert> resultCriteria = cb.createQuery(Expert.class);
            List<Predicate> predicates = new ArrayList<>();
            if (!dto.getFirstName().isEmpty())
                predicates.add(cb.equal(root.get("firstName"), dto.getFirstName()));
            if (!dto.getLastName().isEmpty())
                predicates.add(cb.equal(root.get("lastName"), dto.getLastName()));
            if (!dto.getEmail().isEmpty())
                predicates.add(cb.equal(root.get("email"), dto.getEmail()));
            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }
}