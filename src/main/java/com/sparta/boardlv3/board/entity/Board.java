package com.sparta.boardlv3.board.entity;

import com.sparta.boardlv3.board.dto.BoardRequestDto;
import com.sparta.boardlv3.comment.entity.Comment;
import com.sparta.boardlv3.common.timestamped.TimeStamped;
import com.sparta.boardlv3.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "Board", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Board (BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
