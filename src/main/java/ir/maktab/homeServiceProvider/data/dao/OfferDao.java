package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Lazy
@Repository

public interface OfferDao extends JpaRepository<Offer, Long> {

    @Modifying
  //  @Query(value = "UPDATE Offer o SET o.status = :status WHERE o.id <> :id ")
    @Query(value = "UPDATE offer o SET o.status =:status WHERE o.id <> :id And orders_id=:orderId;",nativeQuery = true)
    void updateOfferStatus(@Param("status") OfferStatus status, @Param("id") Long offerId,@Param("orderId") Long orderId);

    //todo چک کن فقط اونایی نشونش بده که باید انتظار باشن and s.state='')
    @Query(value = "from Offer O join fetch O.orders S where S.id=:id ")
    List<Offer> findAllOfferOfAnOrders(@Param("id") Long OrderId);//is exist

    List<Offer> findByOrders(Orders order, Sort firstSort);

    Optional<Offer> findByOrdersAndExpert(Orders order, Expert expert);
}