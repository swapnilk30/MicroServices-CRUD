package com.example.controller;

import com.example.dto.OrderEvent;
import com.example.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@PostMapping
	public void produceOrderEvent(@RequestBody OrderEvent orderEvent){
		log.info("Producing order event: {}", orderEvent);
		kafkaProducerService.publishOrderEvent(orderEvent);
		log.info("Order event published successfully");

	}

}
