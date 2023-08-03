package com.sparta.boardlv3.user.controller;

import com.sparta.boardlv3.common.api.ApiResponseDto;
import com.sparta.boardlv3.common.jwt.JwtUtil;
import com.sparta.boardlv3.user.dto.AuthRequestDto;
import com.sparta.boardlv3.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signUp(@RequestBody AuthRequestDto authRequestDto) {

        try {
            userService.signup(authRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("중복된 Id 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse servletResponse) {
        try {
            userService.login(authRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST.value()));
        }

        servletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(authRequestDto.getUsername(), authRequestDto.getRole()));

        return ResponseEntity.ok().body(new ApiResponseDto("로그인 성공", HttpStatus.CREATED.value()));
    }
}
