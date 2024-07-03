package com.sparta.easyspring.postlike.repository.inf;

import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.postlike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepositoryInterface extends JpaRepository<PostLike, Long> {
    PostLike findByUserAndPost(User user, Post post);
}
