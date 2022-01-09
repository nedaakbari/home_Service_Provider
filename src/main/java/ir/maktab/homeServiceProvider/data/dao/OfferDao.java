package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Lazy
@Repository
public interface OfferDao extends PagingAndSortingRepository<Offer, Integer> {
/*

    //public void save(Offer offer) ;
    //public void delete(Offer offer) ;
    //List<Offer> findAll() ;


 */
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


    //"From Offer o where o.id=:id"
    Optional<Offer> findOfferById(int id);
*/


}