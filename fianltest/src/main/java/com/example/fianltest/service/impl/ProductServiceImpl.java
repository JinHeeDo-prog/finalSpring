package com.example.fianltest.service.impl;

import com.example.fianltest.dao.ProductDAO;
import com.example.fianltest.dto.ProductDto;
import com.example.fianltest.dto.ProductResponseDto;
import com.example.fianltest.entity.Product;
import com.example.fianltest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> listProductByName(String name) {
        List<Product> product = productDAO.selectProductByName(name);
        List<ProductResponseDto> productResponseDtoList =
                product.stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changeProduct = productDAO.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        return productResponseDto;

    }
    @Override
    public List<ProductResponseDto> getProductByName(String name) {
        List<Product> product = productDAO.selectProductByName(name);
        List<ProductResponseDto> productResponseDtoList =
                product.stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getProductAll() {
        List<Product> product = productDAO.listProductAll();
        List<ProductResponseDto> productResponseDtoList = product.stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getProductByNumber(int number) {
        Product product = productDAO.ProductByNumber(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(product.getName());
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }


    @Override
    public List<ProductResponseDto> listProductByPriceAsc() {
        return productDAO.listProductByPriceAsc().stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }


}
