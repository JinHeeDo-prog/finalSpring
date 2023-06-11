package com.example.fianltest.controller;

import com.example.fianltest.dto.*;
import com.example.fianltest.entity.Board;
import com.example.fianltest.entity.User;
import com.example.fianltest.repository.BoardRepository;
import com.example.fianltest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardService boardService, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
    }
    @PostMapping()
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardDto boardDto){
        BoardResponseDto boardResponseDto = boardService.saveBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteBoard(@AuthenticationPrincipal User loginUser, Long number, String name) throws Exception{
            Optional<Board> selectBoard = boardRepository.findById(number);
            Board board = selectBoard.get();
            if(board.getName().equals(name)) {
                boardService.deleteBoard(number,name);
                return ResponseEntity.status(HttpStatus.OK).body("정상삭제완료");
            }
            else {
                Exception ex = new Exception("이름과 아이디가 같아야 합니다");
                throw ex;
            }

    }

    @PutMapping()
    public ResponseEntity<BoardResponseDto> changeBoardId(@AuthenticationPrincipal User loginUser,  ChangeBoardDto changeBoardDto,String name) throws Exception{
        BoardResponseDto boardResponseDto = boardService.changeBoard(changeBoardDto.getId(), changeBoardDto.getTitle(), changeBoardDto.getContents(),name);
        if(boardResponseDto.getUserName().equals(name)){
            return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
        }
        else {
            Exception exception = new Exception("자기거만 삭제");
            throw exception;
        }
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
