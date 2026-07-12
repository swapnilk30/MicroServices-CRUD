package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer>{
	
	
	boolean existsByMerchantId(int merchantId);

}
