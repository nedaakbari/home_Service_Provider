package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends PagingAndSortingRepository<Customer, Integer> {
    @Modifying
    @Query(value = "UPDATE Customer c set c.password =:password where c.id=:id")
    void UpdatePassword(@Param("password") String password, @Param("id") int id);

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
/*
    //"From Customer C Where C.password = :password and  C.username=:username"
    Optional<Customer> findByUsernameAndPassword(String userName, String password);

    //"FROM Customer C WHERE C.email=:email"
    Optional<Customer> findByEmail(String email);*/


}
