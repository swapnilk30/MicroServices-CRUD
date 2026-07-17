package com.example.dto;

public record OrderEvent
        (Long orderId,
                         String productName,
                         Integer quantity) {
}
