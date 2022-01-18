package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Lazy
@Repository
public interface OrderDao extends JpaRepository<Orders, Long> {

    @Query(value = "from Orders O join fetch O.subCategory S where S.id=:id and O.state='WAITING_FOR_EXPERT_SUGGESTION'")
    List<Orders> findOrdersOfSubService(@Param("id") int serviceId);

    @Query(value = "from Orders O join fetch O.customer C where C.id=:id ")
    List<Orders> findOrderOfCustomer(@Param("id")int customerId);
    //todo and O.state NOT IN ('PAID')

}
