package com.sparta.easyspring.post.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.post.entity.QPost;
import com.sparta.easyspring.postlike.entity.QPostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> getAllPostByLike(List<Long> postIds, long offset, Pageable pageable){
        QPost post = QPost.post;
        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC,post.createdAt);

        List<Post> result = jpaQueryFactory.selectFrom(post)
                .where(post.id.in(postIds))
                .offset(offset)
                .limit(5)
                .orderBy(orderSpecifier)
                .fetch();

        long total = jpaQueryFactory.selectFrom(post)
                .where(post.id.in(postIds))
                .fetchCount();

        return new PageImpl<>(result,pageable,total);
    }
    public List<Long> getAllLikeByUser(Long userId) {
        QPostLike postLike = QPostLike.postLike;

        return jpaQueryFactory.select(postLike.post.id)
                .from(postLike)
                .where(postLike.user.id.eq(userId))
                .fetch();
    }
}
