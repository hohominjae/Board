package com.sparta.boardlv3.board.repository;

import com.sparta.boardlv3.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository <Board, Long>  {
}
