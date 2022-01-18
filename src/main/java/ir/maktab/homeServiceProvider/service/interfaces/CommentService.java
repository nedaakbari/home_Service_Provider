package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.CommentDao;
import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.dto.CommentDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface CommentService {

    void save(Comment comment);

    void delete(Comment comment);

    List<CommentDto> getAll();

    Comment getById(Long theId);

    List<CommentDto> findAllCommentOfAnOrder();
}
