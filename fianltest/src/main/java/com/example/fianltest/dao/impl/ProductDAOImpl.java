package com.example.fianltest.dao.impl;

import com.example.fianltest.dao.ProductDAO;
import com.example.fianltest.dto.ProductDto;
import com.example.fianltest.entity.Product;
import com.example.fianltest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {
    private final ProductRepository productRepository;
    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }
    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public List<Product> selectProductByName(String name) {
        List<Product> selectProduct =
                productRepository.findByName(name, Sort.by(Sort.Order.asc("price")));
        return selectProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        // delete
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else throw new Exception();
    }

    @Override
    public List<Product> listProductAll() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProductName(Product product) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(product.getNumber());
        Product updateProduct;
        if(selectedProduct.isPresent()) {
            Product change= selectedProduct.get();
            product.setName(change.getName());
            product.setPrice(change.getPrice());
            product.setStock(change.getStock());
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updateProduct;
    }

    @Override
    public List<Product> listProductByPriceAsc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public Product ProductByNumber(int number) {
        return productRepository.findProductByNumber(number);
    }

    @Override
    public Product ProductByName(String name) {
        return productRepository.findProductByName(name);
    }
}
