package com.sparta.boardlv3.board.controller;

import com.sparta.boardlv3.board.dto.BoardListResponseDto;
import com.sparta.boardlv3.board.dto.BoardRequestDto;
import com.sparta.boardlv3.board.dto.BoardResponseDto;
import com.sparta.boardlv3.board.service.BoardService;
import com.sparta.boardlv3.common.api.ApiResponseDto;
import com.sparta.boardlv3.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto boardRequestDto){
        BoardResponseDto boardResponseDto = boardService.createBoard(boardRequestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(boardResponseDto);
    }

    @GetMapping("/boards")
    public ResponseEntity<BoardListResponseDto> getBoards(){
        BoardListResponseDto boardList = boardService.getBoards();

        return ResponseEntity.ok().body(boardList);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id){
        BoardResponseDto boardResponseDto = boardService.getBoardById(id);

        return ResponseEntity.ok().body(boardResponseDto);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<ApiResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            BoardResponseDto boardResponseDto = boardService.updateBoard(id, boardRequestDto, userDetails.getUser());
            return ResponseEntity.ok().body(boardResponseDto);
        } catch (RejectedExecutionException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정할 수 있어요~", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<ApiResponseDto> deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        try {
            boardService.deleteBoard(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("게시글이 삭제되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
