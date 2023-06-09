package com.example.fianltest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private int price;
    private long productId;
    private String productName;
    private String userId;
    private String userName;
}
