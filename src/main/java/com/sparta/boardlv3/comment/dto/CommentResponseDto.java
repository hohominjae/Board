package com.sparta.boardlv3.comment.dto;

import com.sparta.boardlv3.comment.entity.Comment;
import com.sparta.boardlv3.common.timestamped.TimeStamped;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto extends TimeStamped {
    private String body;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
//        super(); 이거 있어야되는 이유 함 물어보자
        this.body = comment.getBody();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
