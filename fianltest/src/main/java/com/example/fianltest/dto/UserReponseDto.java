package com.example.fianltest.dto;

import com.example.fianltest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserReponseDto {


    private String uid;
    private String password;
    private String name;

    private String email;

    public UserReponseDto(User user){
        this.uid = user.getUid();
        this.password = user.getPassword();
        this.name = user.getUsername();
        this.email = user.getEmail();
    }



}
