package com.sparta.boardlv3.comment.controller;

import com.sparta.boardlv3.comment.dto.CommentRequestDto;
import com.sparta.boardlv3.comment.dto.CommentResponseDto;
import com.sparta.boardlv3.comment.service.CommentService;
import com.sparta.boardlv3.common.security.UserDetailsImpl;
import com.sparta.boardlv3.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto commentRequestDto){
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto, userDetails.getUser());

        //얘는 왜 201반환안하고 create반환?
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }
}
