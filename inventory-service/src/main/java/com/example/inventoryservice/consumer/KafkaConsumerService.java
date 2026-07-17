package com.example.inventoryservice.consumer;

import com.example.inventoryservice.dto.OrderEvent;
import com.example.inventoryservice.util.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.example.inventoryservice.util.KafkaConstants.ORDER_EVENT_TOPIC;
@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = ORDER_EVENT_TOPIC, groupId = "inventory-service-group")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        log.info("Received Event : {}", orderEvent);
        // Logic to consume order events from Kafka
        log.info("Order Id: {}", orderEvent.orderId());
        log.info("Product Name: {}", orderEvent.productName());
        log.info("Quantity: {}", orderEvent.quantity());
    }
}
