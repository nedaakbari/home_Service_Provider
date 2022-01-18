package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CommentDao;
import ir.maktab.homeServiceProvider.data.model.entity.Comment;
import ir.maktab.homeServiceProvider.dto.CommentDto;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl /*implements CommentService*/ {
    private ModelMapper mapper = new ModelMapper();
    private final CommentDao commentDao;

    public void save(Comment comment) {
        commentDao.save(comment);
    }

    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    public List<CommentDto> getAll() {
        return commentDao.findAll().stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    public Comment getById(Long theId) {
        Optional<Comment> found = commentDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no comment");
    }


    public List<CommentDto> findAllCommentOfAnOrder() {
        //todo
        return null;
    }
}
