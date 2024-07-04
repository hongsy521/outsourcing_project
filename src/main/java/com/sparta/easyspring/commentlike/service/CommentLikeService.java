
package com.sparta.easyspring.commentlike.service;



import com.sparta.easyspring.auth.entity.User;
import com.sparta.easyspring.auth.service.UserService;
import com.sparta.easyspring.comment.entity.Comment;

import com.sparta.easyspring.commentlike.entity.CommentLike;

import com.sparta.easyspring.comment.service.CommentService;
import com.sparta.easyspring.commentlike.repository.impl.CommentLikeRepository;
import com.sparta.easyspring.commentlike.repository.inf.CommentLikeRepositoryInterface;
import com.sparta.easyspring.exception.CustomException;
import com.sparta.easyspring.exception.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentService commentService;
    private final UserService userService;
    // private final CommentLikeRepositoryInterface commentLikeRepositoryInterface;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public ResponseEntity<String> likeComment(Long userId, Long commentId, User loginUser) {
        checkUser(userId,loginUser);

        User user = userService.findUserById(userId);
        Comment comment = commentService.findCommentbyId(commentId);

        Optional<CommentLike> commentLike = commentLikeRepository.findByUserAndComment(user.getId(),comment.getId());

        commentLike.ifPresent(commentLike1 -> {
            throw new CustomException(ErrorEnum.DUPLICATE_LIKE);
        });

        if (comment.getUser().getId().equals(loginUser.getId())) {
            throw new CustomException(ErrorEnum.CANNOT_LIKE_OWN_CONTENT);
        }

        int rowCount = commentLikeRepository.likeComment(user.getId(),comment.getId());
        if(rowCount==1){
            commentService.increaseLikes(commentId);
        }

        return ResponseEntity.ok("댓글 좋아요 완료");
    }

    @Transactional
    public ResponseEntity<String> unlikeComment(Long userId, Long commentId, User loginUser) {
        checkUser(userId,loginUser);

        User user = userService.findUserById(userId);
        Comment comment = commentService.findCommentbyId(commentId);

        commentLikeRepository.findByUserAndComment(user.getId(),comment.getId()).orElseThrow(
                ()->  new CustomException(ErrorEnum.LIKE_NOT_FOUND));

        Long rowCount = commentLikeRepository.unlikeComment(user.getId(),comment.getId());

        if(rowCount==1){
            commentService.decreaseLikes(commentId);
        }

        return ResponseEntity.ok("댓글 좋아요 해제 완료");
    }

    private void checkUser(Long userId, User loginUser) {
        if(!userId.equals(loginUser.getId())){
            throw new CustomException(ErrorEnum.USER_NOT_AUTHENTICATED);
        }
    }
}
