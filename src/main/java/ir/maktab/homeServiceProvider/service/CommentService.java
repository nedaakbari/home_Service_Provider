package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
