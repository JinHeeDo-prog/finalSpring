package com.example.fianltest.dto;

import com.example.fianltest.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {
    private String title;
    public  BoardResponseDto(){}

    public BoardResponseDto(String title, String userId, String userName, String contents) {
        this.title = title;
        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
    }
    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.userId = board.getUserId();
        this.userName = board.getUserName();
        this.contents = board.getContents();
    }

    private String userId;
    private String userName;
    private String contents;
}
