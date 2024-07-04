package com.sparta.easyspring.postlike.repository;

import com.sparta.easyspring.postlike.entity.PostLike;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepositoryCustom {
    Optional<PostLike> findByUserAndPost(Long userId, Long postId);
    int likePost(Long userId, Long postId);
    Long unlikePost(Long userId, Long postId);
}
