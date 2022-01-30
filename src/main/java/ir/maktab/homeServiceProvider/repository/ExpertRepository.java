package ir.maktab.homeServiceProvider.repository;

import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.enums.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.dto.ExpertProjectionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Integer> {

    Optional<Expert> findByEmail(String email);

    Optional<Expert> findByUsernameAndPassword(String userName, String password);
    Optional<Expert> findByEmailAndPassword(String email, String password);

    @Query(value = "From Expert e JOIN fetch e.subCategoryList s where s.title =:title")
    List<Expert> findExpertsOfASubCategory(@Param("title") String title);//چه اکسپرت هایی داخل این هستن


    @Modifying
    @Transactional
    @Query(value = "update Expert e set e.creditCart=:credit where  e.email=:email")
    void updateCreditCart(@Param("email") String email, @Param("credit") double credit);

    @Transactional
    @Modifying
    @Query(value = "update Expert e set e.password=:password where   e.email=:email")
    void updatePassword(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "update Expert set score=:score where  email=:email")
    void updateScore(@Param("email") String email, @Param("score") double score);

    @Modifying
    @Transactional
    @Query(value = "update Expert e set e.status=:status where  e.email=:email")
    void updateStatus(@Param("email") String email, @Param("status") UserRegistrationStatus status);

    @Modifying
    @Transactional
    @Query(value = "update Expert e set e.subCategoryList=:list where e.email=:email")
    void updateServiceList(@Param("email") String email, @Param("list") Set<SubCategory> list);

    @Query("select e.firstName as firstName,e.lastName as lastName ,e.score as score from Expert e")
    List<ExpertProjectionDto> findMyList();//projection کدوما رو برام انتخاب کنه
}
