package com.example.fianltest.service.impl;

import com.example.fianltest.dao.OrderDAO;
import com.example.fianltest.dao.ProductDAO;
import com.example.fianltest.dto.OrderDto;
import com.example.fianltest.dto.OrderResponseDto;
import com.example.fianltest.entity.Order;
import com.example.fianltest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final ProductDAO productDAO;
    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    @Override
    public OrderResponseDto saveOrder(OrderDto orderDto) {

        Order order = new Order();

        order.setUserId(orderDto.getUserId());
        order.setUserName(orderDto.getUserName());
        order.setPrice(orderDto.getPrice());
        order.setProductId(orderDto.getProductId());
        order.setProductName(orderDto.getProductName());


        Order saveOrder = orderDAO.insertOrder(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setUserId(saveOrder.getUserId());
        orderResponseDto.setUserName(saveOrder.getUserName());
        orderResponseDto.setPrice(saveOrder.getPrice());
        orderResponseDto.setProductId(saveOrder.getProductId());
        orderResponseDto.setProductName(saveOrder.getProductName());

        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> getOrderById(long id) {
        List<Order> orders = orderDAO.listOrderByUserId(id);
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public OrderResponseDto getProductOrderById(long id) {
        Order order = orderDAO.orderById(id);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setUserId(order.getUserId());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setProductId(order.getProductId());
        orderResponseDto.setProductName(order.getProductName());
        orderResponseDto.setUserName(order.getUserName());

        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> getOrderByProductId(String id) {
        List<Order> orders = orderDAO.listOrderByProductId(id);
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public List<OrderResponseDto> getOrderdAll() {
        List<Order> boards = orderDAO.listOrderAll();
        List<OrderResponseDto> orderResponseDto = boards.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDto;
    }
}
