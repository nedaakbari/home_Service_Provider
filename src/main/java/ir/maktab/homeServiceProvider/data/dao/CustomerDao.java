package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    //public void save(Customer customer) ;
    //public void delete(Customer customer) ;
    // public List<Customer> findAll();

    //"FROM Customer C WHERE C.email=:email"
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(String email);

    //"From Customer C Where C.password = :password and  C.username=:username"
    Optional<Customer> findByUsernameAndPassword(String userName, String password);


    @Modifying
    @Query(value = "UPDATE Customer c set c.password =:password where c.id=:id")
    void UpdatePassword(@Param("password") String password, @Param("id") int id);


    @Modifying
    @Query(value = "UPDATE Customer c set c.creditCart =:creditCart where c.id=:id")
    void UpdateCreditCart(@Param("creditCart") Long creditCart, @Param("id") int id);

}
