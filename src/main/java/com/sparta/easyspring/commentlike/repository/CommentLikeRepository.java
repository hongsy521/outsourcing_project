
package com.sparta.easyspring.commentlike.repository;


import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.comment.entity.Comment;
import com.sparta.easyspring.comment.repository.CommentRepositoryCustom;
import com.sparta.easyspring.commentlike.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike,Long>, CommentLikeRepositoryCustom {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
