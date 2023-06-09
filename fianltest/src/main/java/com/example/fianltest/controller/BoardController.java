package com.example.fianltest.controller;

import com.example.fianltest.dto.*;
import com.example.fianltest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping()
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardDto boardDto){
        BoardResponseDto boardResponseDto = boardService.saveBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteBoard(Long number) throws Exception{
        boardService.deleteBoard(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @PutMapping
    public ResponseEntity<BoardResponseDto> changeBoardId(@RequestBody ChangeBoardDto changeBoardDto) throws Exception{
        BoardResponseDto boardResponseDto = boardService.changeBoard(changeBoardDto.getId(), changeBoardDto.getTitle(), changeBoardDto.getContents());
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> getBoard() {
        List<BoardResponseDto> boardResponseDtos = boardService.getBoardAll();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDtos);
    }
    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardResponseDto>> getBoardList() {
        List<BoardResponseDto> boardResponseDtos = boardService.getBoardByOrderByCreated();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDtos);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<BoardResponseDto>> getBoardByNameList(String name) {
        List<BoardResponseDto> boardResponseDtos = boardService.getBoardByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDtos);
    }
    @GetMapping("/")
    public ResponseEntity<BoardResponseDto> getBoard(String UserId) {
        BoardResponseDto boardResponseDto = boardService.getBoardByUserId(UserId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }





}
