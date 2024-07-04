package com.sparta.easyspring.follow.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.follow.entity.Follow;
import com.sparta.easyspring.follow.entity.QFollow;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Follow findByFollowingIdAndUser(Long followingId, User user){
        QFollow follow = QFollow.follow;

        return jpaQueryFactory.selectFrom(follow)
                .where(follow.followingId.eq(followingId),follow.user.id.eq(user.getId()))
                .fetchOne();
    }

    @Override
    public long followUser(Long followingId, User user){
        return entityManager.createNativeQuery("INSERT INTO follow (following_id,follower_id) VALUES (?,?)")
                .setParameter(1,followingId)
                .setParameter(2,user.getId())
                .executeUpdate();
    }

    @Override
    public long unfollowUser(Long followingId, User user){
        QFollow follow = QFollow.follow;

        return jpaQueryFactory.delete(follow)
                .where(follow.followingId.eq(followingId),follow.user.id.eq(user.getId()))
                .execute();
    }

    @Override
    public List<Long> getFollowingId(Long userId){
        QFollow follow = QFollow.follow;

        return jpaQueryFactory.select(follow.followingId)
                .from(follow)
                .where(follow.user.id.eq(userId))
                .fetch();
    }
}