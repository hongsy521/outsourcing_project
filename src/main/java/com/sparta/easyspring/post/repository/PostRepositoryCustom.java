package com.sparta.easyspring.post.repository;

import com.sparta.easyspring.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoryCustom {
    Page<Post> getAllPostByLike(List<Long> postIds, long offset, Pageable pageable);

    List<Long> getAllLikeByUser(Long userId);
}
