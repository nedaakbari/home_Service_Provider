package ir.maktab.homeServiceProvider.data.dao;


import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {

    Optional<SubCategory> findByTitle(String title);

    @Query(value = "From SubCategory S JOIN fetch S.category where S.category.id=:id")// ؟؟؟نیازی به این هست؟؟؟
    List<SubCategory> findSubCategoryByCategoryId(@Param("id") int categoryId);

    @Query(value = "From SubCategory S JOIN fetch S.experts E where E.id=:id")
    List<SubCategory> findSubCategoryOfExpert(@Param("id")int expertId);

    @Query(value = "From Expert e JOIN fetch e.subCategoryList s where s.id=:id")// ؟؟؟نیازی به این هست؟؟؟
    Set<Expert> findExpertsOfASubCategory(@Param("id") int id);
}
