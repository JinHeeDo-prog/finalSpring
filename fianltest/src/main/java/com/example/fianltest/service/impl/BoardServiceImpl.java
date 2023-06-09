package com.example.fianltest.service.impl;

import com.example.fianltest.dao.BoardDAO;
import com.example.fianltest.dto.BoardDto;
import com.example.fianltest.dto.BoardResponseDto;
import com.example.fianltest.dto.ProductResponseDto;
import com.example.fianltest.entity.Board;
import com.example.fianltest.entity.Product;
import com.example.fianltest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public BoardResponseDto saveBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setUserId(boardDto.getUserId());
        board.setUserName(boardDto.getUserName());

        Board saveBoard = boardDAO.insertBoard(board);



        BoardResponseDto boardResponseDto = new BoardResponseDto();

        boardResponseDto.setUserId(saveBoard.getUserId());
        boardResponseDto.setUserName(saveBoard.getUserName());
        boardResponseDto.setContents(saveBoard.getContents());
        boardResponseDto.setTitle(saveBoard.getTitle());

        return boardResponseDto;
    }

    @Override
    public void deleteBoard(Long number) throws Exception {
        boardDAO.deleteProduct(number);
    }

    @Override
    public BoardResponseDto changeBoard(Long number, String title, String content) throws Exception {
        Board changeBoard = boardDAO.updateBoard(number, title, content);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        return boardResponseDto;
    }

    @Override
    public List<BoardResponseDto> getBoardAll() {
        List<Board> boards = boardDAO.listBoardAll();
        List<BoardResponseDto> boardResponseDtos = boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtos;
    }

    @Override
    public List<BoardResponseDto> getBoardByOrderByCreated() {
        List<Board> boards = boardDAO.listBoardOrderByCreate();
        List<BoardResponseDto> boardResponseDtos = boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtos;
    }



    @Override
    public List<BoardResponseDto> getBoardByName(String name) {
        List<Board> boards = boardDAO.listBoardByName(name);
        List<BoardResponseDto> boardResponseDtos = boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtos;
    }

    @Override
    public BoardResponseDto getBoardByUserId(String UserId) {
        Board board = boardDAO.getBoard(UserId);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setUserId(board.getUserId());
        boardResponseDto.setUserName(board.getUserName());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContents(board.getContents());

        return boardResponseDto;
    }
}
