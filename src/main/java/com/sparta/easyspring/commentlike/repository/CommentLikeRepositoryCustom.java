package com.sparta.easyspring.commentlike.repository;

import com.sparta.easyspring.commentlike.entity.CommentLike;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentLikeRepositoryCustom {
    Optional<CommentLike> getByUserAndComment(Long userId, Long commentId);
    int likeComment(Long userId, Long commentId);
    Long unlikeComment(Long userId, Long commentId);
    List<Long> getAllLikeByUser(Long userId);
}
