package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Lazy
@Repository

public interface OfferDao extends JpaRepository<Offer, Long> {
    //public void save(Offer offer) ;
    //public void delete(Offer offer) ;
    //List<Offer> findAll() ;

    //"From Offer o where o.id=:id"
    Optional<Offer> findOfferById(int id);

    @Modifying
    @Query(value = "UPDATE Offer o SET o.status = :status WHERE o.id <> :id ")
        //UPDATE offer SET STATUS = 'REJECTED' WHERE id <> 1; این تگ کوته مشکلی ایجاد نمیکنه؟  و نمیتونم توی یه کوئری جمعش کنم؟؟؟
    void updateOfferStatus(@Param("status") OfferStatus status, @Param("id") Long id);

/*   public void update(Offer offer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(offer);
        transaction.commit();
        session.close();
    }*//*



     */
/*public List<Offer> findAllOfferOfAnOrder(int OrderId) {//is exist
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Offer O join fetch O.orders S where S.id=:id ");//todo چک کن فقط اونایی نشونش بده که باید انتظار باشن and s.state=''
        query.setParameter("id", OrderId);
        List<Offer> offers = query.list();
        transaction.commit();
        session.close();
        return offers;
    }*//*



     */


}