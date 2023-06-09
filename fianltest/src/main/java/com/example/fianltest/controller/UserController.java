package com.example.fianltest.controller;

import com.example.fianltest.dto.UserReponseDto;
import com.example.fianltest.entity.User;
import com.example.fianltest.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDetailService userDetailService;


    @Autowired
    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<UserReponseDto>> allProduct(){
        List<UserReponseDto> userReponseDtos = userDetailService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userReponseDtos);
    }

    @GetMapping("/listOrderByName")
    public ResponseEntity<List<UserReponseDto>> allProductByNameAsc(){
        List<UserReponseDto> userReponseDtos = userDetailService.findByNameOrderByNameAsc();
        return ResponseEntity.status(HttpStatus.OK).body(userReponseDtos);
    }
}



