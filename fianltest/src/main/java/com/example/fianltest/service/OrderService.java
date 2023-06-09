package com.example.fianltest.service;

import com.example.fianltest.dto.OrderDto;
import com.example.fianltest.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto saveOrder(OrderDto orderDto);

    List<OrderResponseDto> getOrderdAll();

    List<OrderResponseDto> getOrderById(long id);

    List<OrderResponseDto> getOrderByProductId(String id);

    OrderResponseDto getProductOrderById(long id);
}
