package ir.maktab.homeServiceProvider.data.dao;

import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    //"From MainService M Where M.name=:name"
    Optional<Category> findByTitle(String title);

    //"From MainService M Where M.id=:id"
    Optional<Category> findById(int id);

    @Modifying
    @Query(value = "update Category c set c.title=:title where c.id=:id")
    void updateTitle(@Param("title") String title, @Param("id") int id);


}
