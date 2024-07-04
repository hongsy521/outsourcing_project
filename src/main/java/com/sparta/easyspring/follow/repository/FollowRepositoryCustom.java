package com.sparta.easyspring.follow.repository;

import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.follow.entity.Follow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepositoryCustom {
    Follow findByFollowingIdAndUser(Long followingId, User user);
    long followUser(Long followingId, User user);
    long unfollowUser(Long followingId, User user);
    List<Long> getFollowingId(Long userId);
}
