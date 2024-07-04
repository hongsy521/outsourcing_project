package com.sparta.easyspring.postlike.repository;

import com.sparta.easyspring.postlike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long>, PostLikeRepositoryCustom{
    // PostLike findByUserAndPost(User user, Post post);
}
