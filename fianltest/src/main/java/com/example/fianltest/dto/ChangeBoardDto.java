package com.example.fianltest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeBoardDto {

    private long id;
    private String contents;
    private String title;
    private String userId;
}
