package com.example.fianltest.service;

import com.example.fianltest.dto.UserReponseDto;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDetailService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserReponseDto> findAll();

    List<UserReponseDto> findByNameOrderByNameAsc();
}
