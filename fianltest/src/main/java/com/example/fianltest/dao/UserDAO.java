package com.example.fianltest.dao;

import com.example.fianltest.entity.User;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserDAO {
    List<User> allUser();

    List<User> allUserByNameAsc();



}
