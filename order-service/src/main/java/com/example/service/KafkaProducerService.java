package com.example.service;

import com.example.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.example.util.KafkaConstants.ORDER_EVENT_TOPIC;

@Service
@Slf4j
public class KafkaProducerService {


    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void publishOrderEvent(OrderEvent orderEvent) {
        log.info("Producing order event: {}", orderEvent);
        kafkaTemplate.send(ORDER_EVENT_TOPIC, orderEvent);
    }
}
