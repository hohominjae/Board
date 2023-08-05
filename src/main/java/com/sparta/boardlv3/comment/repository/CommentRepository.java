package com.sparta.boardlv3.comment.repository;

import com.sparta.boardlv3.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
