package ir.maktab.homeServiceProvider.data.repository;

import ir.maktab.homeServiceProvider.data.entity.Person.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByUserNameAndPassWord(String userName, String password);

    Optional<Admin> findByEmail(String email);

}
