package ir.maktab.homeServiceProvider.data.dao;


import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {

    //"From SubService S where S.name=:name"
    Optional<SubCategory> findByTitle(String title);

    //@Query(value = "From SubCategory S JOIN fetch S.category where S.category.id=:id")// ؟؟؟نیازی به این هست؟؟؟
    List<SubCategory> findSubCategoryByCategoryTitle(int categoryId);

    @Query(value = "From SubCategory S JOIN fetch S.experts E where E.id=:id")
    public List<SubCategory> findSubCategoryOfExpert(int expertId);

    //public void update(SubCategory subCategory) {}

}
