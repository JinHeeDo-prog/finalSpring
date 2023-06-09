package com.example.fianltest.repository;


import com.example.fianltest.entity.User;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);



    List<User> findAllByOrderByNameAsc();
}
