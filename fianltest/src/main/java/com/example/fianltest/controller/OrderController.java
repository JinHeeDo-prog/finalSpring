package com.example.fianltest.controller;

import com.example.fianltest.dto.*;
import com.example.fianltest.service.OrderService;
import com.example.fianltest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<OrderResponseDto> createBoard(@RequestBody OrderDto orderDto){
        OrderResponseDto orderResponseDto = orderService.saveOrder(orderDto);

        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderResponseDto>> getBoard() {
        List<OrderResponseDto> orderResponseDto = orderService.getOrderdAll();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

    @GetMapping("/listByUserId")
    public ResponseEntity<List<OrderResponseDto>> getOrderById(long id) {
        List<OrderResponseDto> orderResponseDto = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

    @GetMapping("/listByProductId")
    public ResponseEntity<List<OrderResponseDto>> getOrderByProductId(String id) {
        List<OrderResponseDto> orderResponseDto = orderService.getOrderByProductId(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
    @GetMapping("/")
    public ResponseEntity<OrderResponseDto> getProduct(long id) {
        OrderResponseDto orderResponseDto = orderService.getProductOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }


}
