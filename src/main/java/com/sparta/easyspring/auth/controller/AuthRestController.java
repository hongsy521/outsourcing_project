package com.sparta.easyspring.auth.controller;

import com.sparta.easyspring.auth.dto.AuthRequestDto;
import com.sparta.easyspring.auth.dto.AuthResponseDto;
import com.sparta.easyspring.auth.dto.RefreshTokenRequestDto;
import com.sparta.easyspring.auth.dto.UpdatePasswordRequestDto;
import com.sparta.easyspring.auth.security.UserDetailsImpl;
import com.sparta.easyspring.auth.service.KakaoService;
import com.sparta.easyspring.auth.service.NaverService;
import com.sparta.easyspring.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final UserService userService;
    private final KakaoService kakaoService;
    private final NaverService naverService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody AuthRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponseDto> logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.logout(userDetails);
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<AuthResponseDto> withdraw(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.withdraw(userDetails);
    }

    @PutMapping("/update/password")
    public ResponseEntity<AuthResponseDto> updatePassword(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UpdatePasswordRequestDto requestDto) {
        return userService.updatePassword(requestDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestBody RefreshTokenRequestDto requestDto) {
        return userService.refresh(requestDto);
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<AuthResponseDto> kakaoLogin(@RequestParam String code) throws Exception {
        return kakaoService.login(code);
    }

    @GetMapping("/login/naver")
    public ResponseEntity<AuthResponseDto> naverLogin(@RequestParam String code) throws Exception{
        return naverService.login(code);
    }
}
