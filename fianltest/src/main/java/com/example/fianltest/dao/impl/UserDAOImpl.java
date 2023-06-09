package com.example.fianltest.dao.impl;

import com.example.fianltest.config.security.JwtTokenProvider;
import com.example.fianltest.dao.UserDAO;
import com.example.fianltest.entity.User;
import com.example.fianltest.repository.UserRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    private final UserRepository userRepository;


    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> allUserByNameAsc() {
        return userRepository.findAllByOrderByNameAsc();
    }



}
