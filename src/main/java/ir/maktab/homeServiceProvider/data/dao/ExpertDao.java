package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ExpertDao extends JpaRepository<Expert, Integer> {

    Optional<Expert> findByEmail(String email);

    Optional<Expert> findByUsernameAndPassword(String userName, String password);

       @Query(value = "From Expert e JOIN fetch e.subCategoryList s where s.id=:id")// ؟؟؟نیازی به این هست؟؟؟
       List<Expert> findExpertsOfASubCategory(@Param("id") int id);

 /*  // @Query(value = "From SubCategory S JOIN fetch S.experts E where E.id=:id")
   @Query(value = "From Expert e JOIN fetch e.subCategoryList s where e.id=:id")
   Set<SubCategory> findSubCategoryOfExpert(@Param("id")int subCategoryId);*/
}
