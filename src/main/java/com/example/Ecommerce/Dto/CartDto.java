package com.example.Ecommerce.Dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class CartDto {
    private Long cartId;
    private Double totalPrice = 0.0;
    private List<ProductDto> products = new ArrayList<>();
}