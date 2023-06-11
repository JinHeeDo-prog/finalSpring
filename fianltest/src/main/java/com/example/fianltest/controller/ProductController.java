package com.example.fianltest.controller;

import com.example.fianltest.dto.ChangeProductDto;
import com.example.fianltest.dto.ProductDto;
import com.example.fianltest.dto.ProductResponseDto;
import com.example.fianltest.entity.Product;
import com.example.fianltest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception{
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ProductDto productDto) throws Exception{
        ProductResponseDto productResponseDto = productService.changeProductName(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);

    }
    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> getProductByName(String name) {
        List<ProductResponseDto> productResponseDto = productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> getProduct() {
        List<ProductResponseDto> productResponseDto = productService.getProductAll();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("listOrderByPrice")
    public ResponseEntity<List<ProductResponseDto>> listProductByPriceAsc() {
        List<ProductResponseDto> productResponseDto = productService.listProductByPriceAsc();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<ProductResponseDto> getProduct(int number) {
        ProductResponseDto productResponseDto = productService.getProductByNumber(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }



}
