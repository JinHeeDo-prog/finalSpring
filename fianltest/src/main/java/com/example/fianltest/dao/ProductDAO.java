package com.example.fianltest.dao;

import com.example.fianltest.dto.ProductDto;
import com.example.fianltest.entity.Product;

import java.util.Collection;
import java.util.List;

public interface ProductDAO {
    Product insertProduct(Product product);

    List<Product> selectProductByName(String name);

    void deleteProduct(Long number) throws Exception;

    List<Product> listProductAll();
    Product updateProductName(Product product) throws Exception;

    List<Product> listProductByPriceAsc();

    Product ProductByNumber(int number);

    Product ProductByName(String name);
}
