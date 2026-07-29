package com.example.product.service.controller;

import com.example.product.service.entity.Product;
import com.example.product.service.repository.ProductRepository;
import com.example.product.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts()
    {
        log.info("Fetching all products");
        return productService.getAllProducts();
    }

}
