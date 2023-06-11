package com.example.fianltest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@AllArgsConstructor
public class BoardDto {
    private String title;
    private String userId;

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return userName;
    }

    public String getContents() {
        return contents;
    }

    private String userName;
    private String contents;
}
