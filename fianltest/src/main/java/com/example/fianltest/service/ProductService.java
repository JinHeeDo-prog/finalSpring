package com.example.fianltest.service;

import com.example.fianltest.dto.ProductDto;
import com.example.fianltest.dto.ProductResponseDto;
import com.example.fianltest.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(ProductDto productDto);

    List<ProductResponseDto> listProductByName(String name);

    void deleteProduct(Long number) throws Exception;

    ProductResponseDto changeProductName(ProductDto productDto) throws Exception;

    List<ProductResponseDto> getProductByName(String name);

    List<ProductResponseDto> getProductAll();

    ProductResponseDto getProductByName2(String name);
    ProductResponseDto getProductByNumber(int number);
    List<ProductResponseDto> listProductByPriceAsc();
}
