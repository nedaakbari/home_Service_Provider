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
public class CommentServiceImpl implements CommentService {
    private final ModelMapper mapper;
    private final CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public List<CommentDto> getAll() {
        return commentDao.findAll().stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
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
