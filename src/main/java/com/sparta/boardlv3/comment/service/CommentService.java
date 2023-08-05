package com.sparta.boardlv3.comment.service;

import com.sparta.boardlv3.board.entity.Board;
import com.sparta.boardlv3.board.service.BoardService;
import com.sparta.boardlv3.comment.dto.CommentRequestDto;
import com.sparta.boardlv3.comment.dto.CommentResponseDto;
import com.sparta.boardlv3.comment.entity.Comment;
import com.sparta.boardlv3.comment.repository.CommentRepository;
import com.sparta.boardlv3.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardService boardService;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user) {
        Board board = boardService.findBoard(commentRequestDto.getPostId());
        Comment comment = new Comment(commentRequestDto.getBody());
        comment.setUser(user);
        comment.setBoard(board);

        //여기는 var타입의 savedComment 를 만들어야하는 이유?
        var savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }
}
