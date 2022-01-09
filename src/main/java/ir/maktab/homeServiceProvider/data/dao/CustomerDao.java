package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao extends PagingAndSortingRepository<Customer, Integer> {
    //public void save(Customer customer) ;
    //public void delete(Customer customer) ;
    // public List<Customer> findAll();

/*    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }*/

    //"From Customer C Where C.password = :password and  C.username=:username"
    Optional<Customer> findByUsernameAndPassword(String userName, String password);

    //"FROM Customer C WHERE C.email=:email"
    Optional<Customer> findByEmail(String email);


}
