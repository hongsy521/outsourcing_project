package com.sparta.easyspring.commentlike.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.commentlike.entity.CommentLike;
import com.sparta.easyspring.commentlike.entity.QCommentLike;
import com.sparta.easyspring.commentlike.repository.inf.CommentLikeRepositoryInterface;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class CommentLikeRepository extends QuerydslRepositorySupport {
    private final CommentLikeRepositoryInterface commentLikeRepositoryInterface;
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public CommentLikeRepository(CommentLikeRepositoryInterface commentLikeRepositoryInterface, JPAQueryFactory jpaQueryFactory,EntityManager entityManager) {
        super(CommentLike.class);
        this.commentLikeRepositoryInterface = commentLikeRepositoryInterface;
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager=entityManager;
    }

    public Optional<CommentLike> findByUserAndComment(Long userId, Long commentId){
        QCommentLike commentLike = QCommentLike.commentLike;

        CommentLike result = jpaQueryFactory.selectFrom(commentLike)
                .where(commentLike.user.id.eq(userId),commentLike.comment.id.eq(commentId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    public int likeComment(Long userId, Long commentId){
        QCommentLike commentLike = QCommentLike.commentLike;

        return entityManager.createNativeQuery("INSERT INTO commentlikes (comment_id,user_id) VALUES (?,?)")
                .setParameter(1,commentId)
                .setParameter(2,userId)
                .executeUpdate();  // 쿼리에 의해 영형을 받은 행의 수
    }

    public Long unlikeComment(Long userId, Long commentId){
        QCommentLike commentLike = QCommentLike.commentLike;

        return jpaQueryFactory.delete(commentLike)
                .where(commentLike.user.id.eq(userId),commentLike.comment.id.eq(commentId))
                .execute();
    }
}
