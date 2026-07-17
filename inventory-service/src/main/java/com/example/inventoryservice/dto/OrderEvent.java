package com.example.inventoryservice.dto;

public record OrderEvent(Long orderId,
                         String productName,
                         Integer quantity) {
}
