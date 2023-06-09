package com.example.fianltest.dao;

import com.example.fianltest.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {
    Board insertBoard(Board board);
    void deleteProduct(Long number) throws Exception;

    Board updateBoard(Long number, String title, String content) throws Exception;

    List<Board> listBoardAll();

    List<Board> listBoardOrderByCreate();

    List<Board> listBoardByName(String name);

    Board getBoard(String userId);

}
