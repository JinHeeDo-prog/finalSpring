package com.example.fianltest.repository;

import com.example.fianltest.entity.Board;
import com.example.fianltest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();

    List<Board> findAllByOrderByCreatedAt();

    List<Board> findBoardByUserName(String name);

    Board findBoardByUserId(String UserId);
}
