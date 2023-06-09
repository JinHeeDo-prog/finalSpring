package com.example.fianltest.dao.impl;

import com.example.fianltest.dao.OrderDAO;
import com.example.fianltest.entity.Order;
import com.example.fianltest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderDAOImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order insertOrder(Order order) {
        Order saveOrder = orderRepository.save(order);
        return saveOrder;
    }

    @Override
    public List<Order> listOrderAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> listOrderByUserId(long id) {
        return orderRepository.findOrderByUserId(id);
    }

    @Override
    public Order orderById(long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> listOrderByProductId(String id) {
        return orderRepository.findOrderByProductId(id);
    }


}
