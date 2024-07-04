package com.sparta.easyspring.postlike.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.postlike.entity.PostLike;
import com.sparta.easyspring.postlike.entity.QPostLike;
import com.sparta.easyspring.postlike.repository.inf.PostLikeRepositoryInterface;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PostLikeRepository extends QuerydslRepositorySupport {

    private final PostLikeRepositoryInterface postLikeRepositoryInterface;
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public PostLikeRepository(PostLikeRepositoryInterface postLikeRepositoryInterface, JPAQueryFactory jpaQueryFactory,EntityManager entityManager) {
        super(PostLike.class);
        this.postLikeRepositoryInterface = postLikeRepositoryInterface;
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager=entityManager;
    }

    public Optional<PostLike> findByUserAndPost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        PostLike result = jpaQueryFactory.selectFrom(postLike)
                .where(postLike.user.id.eq(userId),postLike.post.id.eq(postId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    public int likePost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        return entityManager.createNativeQuery("INSERT INTO postlikes (post_id,user_id) VALUES (?,?)")
                .setParameter(1,postId)
                .setParameter(2,userId)
                .executeUpdate();
    }

    public Long unlikePost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        return jpaQueryFactory.delete(postLike)
                .where(postLike.user.id.eq(userId),postLike.post.id.eq(postId))
                .execute();
    }
}
