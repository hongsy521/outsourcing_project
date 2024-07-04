package com.sparta.easyspring.comment.repository;

import com.sparta.easyspring.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>,CommentRepositoryCustom {
    List<Comment> findAllByPostId(Long postId);
}
