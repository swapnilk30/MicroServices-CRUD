package com.example.product.service.service;

import com.example.product.service.entity.Product;
import com.example.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository  productRepository;

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();

        //products.forEach( product -> System.out.println(product.getCategory().getName()));
        return products;
    }
}
