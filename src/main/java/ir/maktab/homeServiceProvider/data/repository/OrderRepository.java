package ir.maktab.homeServiceProvider.data.repository;

import ir.maktab.homeServiceProvider.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Lazy
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "from Orders O join fetch O.subCategory S where S.id=:id and (O.state='WAITING_FOR_EXPERT_SUGGESTION' or O.state='WAITING_FOR_SELECT_AN_EXPERT') ")
    List<Orders> findOrdersOfSubService(@Param("id") int serviceId);

    @Query(value = "from Orders O join fetch O.customer C where C.id=:id ")
    List<Orders> findOrderOfCustomer(@Param("id") int customerId);

    Optional<Orders> findByCodeNumber(String uuid);

}
