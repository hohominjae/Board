package com.sparta.boardlv3.board.repository;

import com.sparta.boardlv3.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository <Board, Long>  {
}
