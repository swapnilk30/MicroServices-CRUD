package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.MerchantService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/merchant")
@Slf4j
public class MerchantController {

	private final MerchantService merchantService;

	public MerchantController(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam MultipartFile file) {

		// Save uploaded file

		// Launch Spring Batch Job

		return ResponseEntity.ok("Batch Started");
	}


}
