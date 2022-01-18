package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(String email);

    Optional<Customer> findByUsernameAndPassword(String userName, String password);

   /* @Modifying
    @Query("update Customer c set c.password=:newPassword where c.username=:username and c.password=:password")
    void updateCustomerPassword(@Param("username") String username, @Param("password") String password, @Param("newPassword") String newPassword);
*/
}
