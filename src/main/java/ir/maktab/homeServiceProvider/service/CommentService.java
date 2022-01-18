package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CommentDao;
import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.dto.CommentDto;
import ir.maktab.homeServiceProvider.dto.mapper.CommentMapper;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService /*implements Services<Comment, CommentDto, Long>*/ {
    private final CommentMapper mapper;
    private final CommentDao commentDao;

   // @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

   // @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

   // @Override
    public List<CommentDto> getAll() {
        List<Comment> allComments = commentDao.findAll();
        return allComments.stream().map(mapper::commentToDto).collect(Collectors.toList());
    }

   // @Override
    public Comment getById(Long theId) {
        Optional<Comment> found = commentDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no comment");
    }


    public List<CommentDto> findAllCommentOfAnOrder(){
        //todo
        return null;
    }
}
