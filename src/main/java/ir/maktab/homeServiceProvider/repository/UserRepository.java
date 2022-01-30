package ir.maktab.homeServiceProvider.repository;

import ir.maktab.homeServiceProvider.entity.Person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsernameAndPassword(String userName, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}


