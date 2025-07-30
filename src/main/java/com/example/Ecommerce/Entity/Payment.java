package com.example.Ecommerce.Entity;

import com.example.Ecommerce.auth.AuthticationEntities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    UUID paymentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

    private String cardNumber;
    private LocalDate expiryDate ;
    private String cvv;
}
