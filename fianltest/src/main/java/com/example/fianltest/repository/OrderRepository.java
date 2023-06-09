package com.example.fianltest.repository;

import com.example.fianltest.entity.Board;
import com.example.fianltest.entity.Order;
import com.example.fianltest.entity.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    List<Order> findOrderByUserId(long userId);

    List<Order> findOrderByProductId(String id);

    Order findOrderById(long id);
}
