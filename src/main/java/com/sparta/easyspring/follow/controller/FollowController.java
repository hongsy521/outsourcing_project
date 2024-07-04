package com.sparta.easyspring.follow.controller;

import com.sparta.easyspring.auth.security.UserDetailsImpl;
import com.sparta.easyspring.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{followingId}")
    public ResponseEntity<String> addFollow(@PathVariable(name = "followingId") Long followingId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String successMsg = followService.addFollow(followingId,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(successMsg);
    }

    @DeleteMapping("/{followingId}")
    public ResponseEntity<String> deleteFollow(@PathVariable(name = "followingId") Long followingId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        String successMsg = followService.deleteFollow(followingId,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(successMsg);
    }
}
