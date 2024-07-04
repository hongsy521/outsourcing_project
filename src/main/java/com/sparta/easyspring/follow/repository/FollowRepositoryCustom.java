package com.sparta.easyspring.follow.repository;

import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.follow.entity.Follow;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepositoryCustom {
    Follow findByFollowingIdAndUser(Long followingId, User user);
    long followUser(Long followingId, User user);
    long unfollowUser(Long followingId, User user);
}
