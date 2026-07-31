package com.example.pg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pg.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, String>{

}
