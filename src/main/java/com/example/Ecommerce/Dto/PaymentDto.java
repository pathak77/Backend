package com.example.Ecommerce.Dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PaymentDto {
    private long payment_id;

    private String username;
    private String cardNumber;
    private LocalDate expiryDate ;
    private String cvv;
}
