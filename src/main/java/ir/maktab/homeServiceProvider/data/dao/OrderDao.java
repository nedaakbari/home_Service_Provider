package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Lazy
@Repository
public interface OrderDao extends PagingAndSortingRepository<Orders, Integer> {

    //public void save(Orders order);
    //public void delete(Orders order) ;
    //   public List<Orders> findAll() ;

/*    public void update(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }*/


  /*  public List<Orders> findOrdersOfSubService(int serviceId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Orders O join fetch O.subService S where S.id=:id and O.state='WAITING_FOR_EXPERT_SUGGESTION'");
        query.setParameter("id", serviceId);
        List<Orders> orders = query.list();
        transaction.commit();
        session.close();
        return orders;
    }*/

    @Query(value = "from Orders O join fetch O.customer C where C.id=:id ")
//todo and O.state NOT IN ('PAID')
    List<Orders> findOrderOfCustomer(int customerId);

    //"from Orders O where O.id=:id"
    Optional<Orders> findOrderByID(int id);


}
