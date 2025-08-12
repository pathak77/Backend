package com.example.Ecommerce.Entity;


import com.example.Ecommerce.auth.AuthticationEntities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table (name = "addressDetails")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID address_id;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private User user;

    private String street;
    private String city;
    private String state;
    private String zip;
    private int phone;


}
