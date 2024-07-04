package com.sparta.easyspring.postlike.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.postlike.entity.PostLike;
import com.sparta.easyspring.postlike.entity.QPostLike;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostLikeRepositoryImpl implements PostLikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Optional<PostLike> findByUserAndPost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        PostLike result = jpaQueryFactory.selectFrom(postLike)
                .where(postLike.user.id.eq(userId),postLike.post.id.eq(postId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public int likePost(Long userId, Long postId){

        return entityManager.createNativeQuery("INSERT INTO postlikes (post_id,user_id) VALUES (?,?)")
                .setParameter(1,postId)
                .setParameter(2,userId)
                .executeUpdate();
    }

    @Override
    public Long unlikePost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        return jpaQueryFactory.delete(postLike)
                .where(postLike.user.id.eq(userId),postLike.post.id.eq(postId))
                .execute();
    }
    @Override
    public List<Long> getAllLikeByUser(Long userId) {
        QPostLike postLike = QPostLike.postLike;

        return jpaQueryFactory.select(postLike.post.id)
                .from(postLike)
                .where(postLike.user.id.eq(userId))
                .fetch();
    }
}
