package ir.maktab.homeServiceProvider.service.interfaces;



import ir.maktab.homeServiceProvider.data.entity.Comment;
import ir.maktab.homeServiceProvider.data.entity.Orders;
import ir.maktab.homeServiceProvider.data.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import ir.maktab.homeServiceProvider.dto.CommentDto;

import java.util.List;


public interface CommentService {

    void save(Customer customer, Expert expert, Orders orders, String comment);

    void delete(CommentDto commentDto);

    List<CommentDto> getAll();

    CommentDto getById(Long theId);

    List<CommentDto> findAllCommentOfAnOrder(Orders orders);

    Comment findByUUID(CommentDto commentDto);
}