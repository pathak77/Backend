package com.example.Ecommerce.Dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDetailDto {

    private UUID id;
    private ProductDto product;
    private UUID productVariantId;
    private Integer quantity;
    private Double itemPrice;
}