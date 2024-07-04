package com.sparta.easyspring.comment.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.comment.entity.Comment;
import com.sparta.easyspring.comment.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Comment> getAllCommentByLike(List<Long> commentIds, long offset, Pageable pageable){
        QComment comment = QComment.comment1;
        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC,comment.createdAt);

        List<Comment> result = jpaQueryFactory.selectFrom(comment)
                .where(comment.id.in(commentIds))
                .offset(offset)
                .limit(5)
                .orderBy(orderSpecifier)
                .fetch();

        long total = jpaQueryFactory.selectFrom(comment)
                .where(comment.id.in(commentIds))
                .fetchCount();

        return new PageImpl<>(result,pageable,total);
    }
}
