package com.example.fianltest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {
    private String title;
    private String userId;
    private String userName;
    private String contents;
}
