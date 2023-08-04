package com.sparta.boardlv3.board.service;

import com.sparta.boardlv3.board.dto.BoardListResponseDto;
import com.sparta.boardlv3.board.dto.BoardRequestDto;
import com.sparta.boardlv3.board.dto.BoardResponseDto;
import com.sparta.boardlv3.board.entity.Board;
import com.sparta.boardlv3.board.repository.BoardRepository;
import com.sparta.boardlv3.user.entity.User;
import com.sparta.boardlv3.user.entity.UserRoleEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = new Board(boardRequestDto);
        board.setUser(user);

        boardRepository.save(board);

        return new BoardResponseDto(board);
    }


    public BoardListResponseDto getBoards() {
        List<BoardResponseDto> boardList = boardRepository.findAll().stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());

        return new BoardListResponseDto(boardList);
    }

    public BoardResponseDto getBoardById(Long id) {
        Board board = findBoard(id);

        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, User user) {
        Board board = findBoard(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || board.getUser().equals(user))) {
            throw new RejectedExecutionException();
        }

        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());

        return new BoardResponseDto(board);
    }

    public void deleteBoard(Long id, User user) {
        Board board = findBoard(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || board.getUser().equals(user))) {
            throw new RejectedExecutionException();
        }

        boardRepository.delete(board);
    }

    public Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }
}