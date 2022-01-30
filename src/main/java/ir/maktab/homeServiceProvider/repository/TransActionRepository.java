package ir.maktab.homeServiceProvider.repository;

import ir.maktab.homeServiceProvider.entity.TransActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransActionRepository extends JpaRepository<TransActions, Long> {

}
