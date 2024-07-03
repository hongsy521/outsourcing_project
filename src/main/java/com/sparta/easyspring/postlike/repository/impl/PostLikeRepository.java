package com.sparta.easyspring.postlike.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.postlike.entity.PostLike;
import com.sparta.easyspring.postlike.entity.QPostLike;
import com.sparta.easyspring.postlike.repository.inf.PostLikeRepositoryInterface;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostLikeRepository extends QuerydslRepositorySupport {

    private final PostLikeRepositoryInterface postLikeRepositoryInterface;
    private final JPAQueryFactory jpaQueryFactory;

    public PostLikeRepository(PostLikeRepositoryInterface postLikeRepositoryInterface, JPAQueryFactory jpaQueryFactory) {
        super(PostLike.class);
        this.postLikeRepositoryInterface = postLikeRepositoryInterface;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<PostLike> findByUserAndPost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        PostLike result = jpaQueryFactory.selectFrom(postLike)
                .where(postLike.user.id.eq(userId),postLike.post.id.eq(postId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    public Long likePost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        // 삽입된 행의 수 반환
        return jpaQueryFactory.insert(postLike)
                .set(postLike.user.id,userId)
                .set(postLike.post.id,postId)
                .execute();
    }

    public Long unlikePost(Long userId, Long postId){
        QPostLike postLike = QPostLike.postLike;

        // 삽입된 행의 수 반환
        return jpaQueryFactory.delete(postLike)
                .where(postLike.user.id.eq(userId),postLike.post.id.eq(postId))
                .execute();
    }
}
