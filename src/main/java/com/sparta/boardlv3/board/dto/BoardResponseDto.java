package com.sparta.boardlv3.board.dto;

import com.sparta.boardlv3.board.entity.Board;
import com.sparta.boardlv3.common.api.ApiResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class BoardResponseDto extends ApiResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;
//    private List<CommentResponseDto> comments;

    public BoardResponseDto(Board Board) {
        this.id = Board.getId();
        this.title = Board.getTitle();
        this.content = Board.getContent();
        this.createdAt = Board.getCreatedAt();
        this.modifiedAt = Board.getModifiedAt();
        this.username = Board.getUser().getUsername();
//        this.comments = Board.getComments().stream()
//                .map(CommentResponseDto::new)
//                .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()) // 작성날짜 내림차순
//                .toList();
    }
}
