package com.example.fianltest.service.impl;


import com.example.fianltest.dao.UserDAO;
import com.example.fianltest.dto.UserReponseDto;
import com.example.fianltest.entity.User;
import com.example.fianltest.repository.UserRepository;
import com.example.fianltest.service.UserDetailService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // 클래스를 서비스 빈으로 등록해줌
 // 필수 매개변수를 생성자에다 만들어준다
public class UserDetailServiceImpl implements UserDetailService {
    private final UserRepository userRepository;
    private final UserDAO userDao;

    public UserDetailServiceImpl(UserRepository userRepository, UserDAO userDao) {
        this.userRepository = userRepository;
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 메소드
        return userRepository.getByUid(username); //
    }

    @Override
    public List<UserReponseDto> findAll() {
        List<User> users = userDao.allUser();
        List<UserReponseDto> userDtoList = users.stream().map(UserReponseDto::new).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public List<UserReponseDto> findByNameOrderByNameAsc() {
        List<User> users = userDao.allUserByNameAsc();
        List<UserReponseDto> userDotList = users.stream().map(UserReponseDto::new).collect(Collectors.toList());
        return userDotList;
    }



}
