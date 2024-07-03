package com.sparta.easyspring.postlike.service;

import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.auth.service.UserService;
import com.sparta.easyspring.exception.CustomException;
import com.sparta.easyspring.post.entity.Post;
import com.sparta.easyspring.post.service.PostService;
import com.sparta.easyspring.postlike.entity.PostLike;
import com.sparta.easyspring.postlike.repository.impl.PostLikeRepository;
import com.sparta.easyspring.postlike.repository.inf.PostLikeRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sparta.easyspring.exception.ErrorEnum.*;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostService postService;
    private final UserService userService;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public String likePost(long userId, long postId) {
        User user = userService.findUserById(userId);
        Post post = postService.findPostbyId(postId);

        Optional<PostLike> postLike = postLikeRepository.findByUserAndPost(user.getId(),post.getId());

        if(postLike!=null){
            throw new CustomException(DUPLICATE_LIKE);
        }

        if (post.getUser().getId() == userId) {
            throw new CustomException(CANNOT_LIKE_OWN_CONTENT);
        }

        Long rowCount = postLikeRepository.likePost(user.getId(),post.getId());
        if(rowCount==1){
            postService.increaseLikes(postId);
        }

        return "게시글 좋아요 완료";
    }

    @Transactional
    public String unlikePost(long userId, long postId) {
        User user = userService.findUserById(userId);
        Post post = postService.findPostbyId(postId);

        postLikeRepository.findByUserAndPost(user.getId(),post.getId()).orElseThrow(
                ()-> new CustomException(LIKE_NOT_FOUND)
        );
        Long rowCount = postLikeRepository.unlikePost(user.getId(),post.getId());

        if(rowCount==0){
            postService.decreaseLikes(postId);
        }

        return "게시글 좋아요 해제 완료";
    }
}
