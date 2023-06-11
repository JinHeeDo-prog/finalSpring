package com.example.fianltest.service;

import com.example.fianltest.dto.BoardDto;
import com.example.fianltest.dto.BoardResponseDto;
import com.example.fianltest.dto.ProductDto;
import com.example.fianltest.dto.ProductResponseDto;
import com.example.fianltest.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    BoardResponseDto saveBoard(BoardDto boardDto);
    void deleteBoard(Long number, String name) throws Exception;
    BoardResponseDto changeBoard(Long number, String title, String content, String name) throws Exception;

    List<BoardResponseDto> getBoardAll();

    List<BoardResponseDto> getBoardByOrderByCreated();

    List<BoardResponseDto> getBoardByName(String name);

    BoardResponseDto getBoardByUserId(String UserId);
}
