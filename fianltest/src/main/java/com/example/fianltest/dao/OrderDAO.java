package com.example.fianltest.dao;

import com.example.fianltest.entity.Board;
import com.example.fianltest.entity.Order;
import com.example.fianltest.entity.Product;

import java.util.List;

public interface OrderDAO {
    Order insertOrder(Order order);
    List<Order> listOrderAll();

    List<Order> listOrderByUserId(long id);
    List<Order> listOrderByProductId(String id);

    Order orderById(long id);
}
