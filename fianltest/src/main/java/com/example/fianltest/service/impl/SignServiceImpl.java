package com.example.fianltest.service.impl;


import com.example.fianltest.config.security.JwtTokenProvider;
import com.example.fianltest.dto.CommonResponse;
import com.example.fianltest.dto.SignInResultDto;
import com.example.fianltest.dto.SignUpResultDto;
import com.example.fianltest.entity.User;
import com.example.fianltest.repository.UserRepository;
import com.example.fianltest.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;



@Service // 클래스를 서비스 빈으로 등록해줌
public class SignServiceImpl implements SignService {

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository
            , JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto signUp(String id, String password, String name, String email, String role) {
        System.out.println("[signUp] 회원가입");
        User user;
        if(role.equalsIgnoreCase("admin")) {
            user = User.builder().uid(id).name(name).email(email)
                    .password(passwordEncoder.encode(password)) //DB에 비밀번호가 암호화 되어 들어갈수 있게 보안상의 이유로 이와같이 처리
                    .roles(Collections.singletonList("ROLE_ADMIN")).build();
        } else {
            user = User.builder().uid(id).name(name).email(email)
                    .password(passwordEncoder.encode(password)) //DB에 비밀번호가 암호화 되어 들어갈수 있게 보안상의 이유로 이와같이 처리
                    .roles(Collections.singletonList("ROLE_USER")).build();
        }
        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignUpResultDto();
        if(!savedUser.getName().isEmpty()) {
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    private void setSuccessResult(SignUpResultDto signUpResultDto) {
        signUpResultDto.setSuccess(true);
        signUpResultDto.setCode(CommonResponse.SUCCESS.getCode());
        signUpResultDto.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto signUpResultDto) {
        signUpResultDto.setSuccess(false);
        signUpResultDto.setCode(CommonResponse.FAIL.getCode());
        signUpResultDto.setMsg(CommonResponse.FAIL.getMsg());
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        User user = userRepository.getByUid(id);
        if(!passwordEncoder.matches(password, user.getPassword())) { // 패스워드가 일치하지 않는다면 if문
            throw new RuntimeException();
        }
        String token = jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles());
        SignInResultDto signInResultDto = SignInResultDto.builder().token(token).build();
        setSuccessResult(signInResultDto);
        return signInResultDto;
    }
}
