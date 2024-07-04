package com.sparta.easyspring.comment.repository;

import com.sparta.easyspring.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoryCustom {
    Page<Comment> getAllCommentByLike(List<Long> commentIds, long offset, Pageable pageable);
}
