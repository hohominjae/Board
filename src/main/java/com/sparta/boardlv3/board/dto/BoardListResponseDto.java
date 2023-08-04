package com.sparta.boardlv3.board.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardListResponseDto {
    private List<BoardResponseDto> boardsList;

    public BoardListResponseDto(List<BoardResponseDto> boardsList) {
        this.boardsList = getBoardsList();
    }
}