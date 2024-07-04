package com.sparta.easyspring.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ProfileResponseDto {
    private Long id;
    private String username;
    private String introduction;
    private Long postLikeCount;
    private Long commentLikeCount;
}
