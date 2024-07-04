package com.sparta.easyspring.commentlike.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.commentlike.entity.CommentLike;
import com.sparta.easyspring.commentlike.entity.QCommentLike;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Optional<CommentLike> getByUserAndComment(Long userId, Long commentId){
        QCommentLike commentLike = QCommentLike.commentLike;

        CommentLike result = jpaQueryFactory.selectFrom(commentLike)
                .where(commentLike.user.id.eq(userId),commentLike.comment.id.eq(commentId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public int likeComment(Long userId, Long commentId){
        QCommentLike commentLike = QCommentLike.commentLike;

        return entityManager.createNativeQuery("INSERT INTO commentlikes (comment_id,user_id) VALUES (?,?)")
                .setParameter(1,commentId)
                .setParameter(2,userId)
                .executeUpdate();  // 쿼리에 의해 영형을 받은 행의 수
    }

    @Override
    public Long unlikeComment(Long userId, Long commentId){
        QCommentLike commentLike = QCommentLike.commentLike;

        return jpaQueryFactory.delete(commentLike)
                .where(commentLike.user.id.eq(userId),commentLike.comment.id.eq(commentId))
                .execute();
    }

    @Override
    public List<Long> getAllLikeByUser(Long userId) {
        QCommentLike commentLike = QCommentLike.commentLike;

        return jpaQueryFactory.select(commentLike.comment.id)
                .from(commentLike)
                .where(commentLike.user.id.eq(userId))
                .fetch();
    }
}
