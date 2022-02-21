package ir.maktab.homeServiceProvider.data.repository;

import ir.maktab.homeServiceProvider.data.entity.TransActions;
import ir.maktab.homeServiceProvider.data.entity.TransActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransActionRepository extends JpaRepository<TransActions, Long> {

}
