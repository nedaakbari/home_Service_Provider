package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CommentDao;
import ir.maktab.homeServiceProvider.dto.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper mapper;
    private final CommentDao commentDao;

}
