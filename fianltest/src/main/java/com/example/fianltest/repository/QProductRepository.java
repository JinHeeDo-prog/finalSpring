package com.example.fianltest.repository;

import com.example.fianltest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
}
