package com.example.fianltest.repository;

import com.example.fianltest.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name, Sort sort);

    List<Product> findAll();

    List<Product> findAllByOrderByPriceDesc();

    Product findProductByNumber(int number);

    Product findProductByName(String name);


}
