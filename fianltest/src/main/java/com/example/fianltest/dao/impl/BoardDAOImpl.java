package com.example.fianltest.dao.impl;

import com.example.fianltest.dao.BoardDAO;
import com.example.fianltest.entity.Board;
import com.example.fianltest.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BoardDAOImpl implements BoardDAO {
    private final BoardRepository boardRepository;
    @Autowired
    public BoardDAOImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Override
    public Board insertBoard(Board board) {
        Board saveProduct = boardRepository.save(board);
        return saveProduct;
    }

    @Override
    public void deleteProduct(Long number, String name) throws Exception {
        Optional<Board> selectBoard = boardRepository.findById(number);


        // delete
        if (selectBoard.isPresent()) {
            Board board = selectBoard.get();
            if(board.getName().equals(name)) {
                boardRepository.delete(board);
            }
            else {
                Exception ex = new Exception("이름과 아이디가 같아야 합니다");
            }
        } else throw new Exception();
    }

    @Override
    public Board updateBoard(Long number, String title, String content, String name) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(number);
        Board updateBoard;
        if(selectedBoard.isPresent()) {
            Board board = selectedBoard.get();
            board.setTitle(title);
            board.setContents(content);
            board.setUpdatedAt(LocalDateTime.now());
            board.setUserName(name);
            updateBoard = boardRepository.save(board);
        } else {
            throw new Exception();
        }

        return updateBoard;
    }

    @Override
    public List<Board> listBoardAll() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> listBoardOrderByCreate() {
        return boardRepository.findAllByOrderByCreatedAt();
    }

    @Override
    public List<Board> listBoardByName(String name) {
        List<Board> selectBoard =
                boardRepository.findBoardByUserName(name);
        return selectBoard;
    }

    @Override
    public Board getBoard(String userId) {
        return boardRepository.findBoardByUserId(userId);
    }


}
