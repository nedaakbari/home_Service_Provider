package ir.maktab.homeServiceProvider.repository;

import ir.maktab.homeServiceProvider.entity.Comment;
import ir.maktab.homeServiceProvider.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCodeNumber(String uuid);

    @Query(value = "from Comment c where c.order=:order")
    List<Comment> findAllCommentOfAnOrder(@Param("order") Orders order);

}
