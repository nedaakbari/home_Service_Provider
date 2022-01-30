package ir.maktab.homeServiceProvider.repository;

import ir.maktab.homeServiceProvider.entity.Offer;
import ir.maktab.homeServiceProvider.entity.Orders;
import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.enums.OfferStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Lazy
@Repository

public interface OfferRepository extends CrudRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {

    @Query(value = "from Offer O join fetch O.orders S where S.id=:id ")
    List<Offer> findAllOfferOfAnOrders(@Param("id") Long id);

    List<Offer> findByOrders(Orders order, Sort Sort);

    Optional<Offer> findByOrdersAndExpert(Orders order, Expert expert);

    Optional<Offer> findOfferByCodeNumber(String uuid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE offer o SET o.status =:status WHERE o.id <> :id And orders_id=:orders_id", nativeQuery = true)
    void updateOfferStatus(@Param("status") OfferStatus status, @Param("id") Long offerId, @Param("orders_id") Long orders_id);
}