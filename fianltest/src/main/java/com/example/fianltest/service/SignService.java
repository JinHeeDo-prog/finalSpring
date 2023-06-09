package com.example.fianltest.service;


import com.example.fianltest.dto.SignInResultDto;
import com.example.fianltest.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp (String id, String password, String name, String email, String role);
    SignInResultDto signIn (String id, String password) throws RuntimeException;
}
