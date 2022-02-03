package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.entity.Comment;
import ir.maktab.homeServiceProvider.data.entity.Orders;
import ir.maktab.homeServiceProvider.dto.CommentDto;

import java.util.List;


public interface CommentService {

    void save(CommentDto commentDto);

    void delete(CommentDto commentDto);

    List<CommentDto> getAll();

    CommentDto getById(Long theId);

    List<CommentDto> findAllCommentOfAnOrder(Orders orders);

    Comment findByUUID(CommentDto commentDto);
}