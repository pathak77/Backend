package com.example.Ecommerce.Model;


import com.example.Ecommerce.auth.AuthticationEntities.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "addressDetails")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long address_id;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private User user;

    private String street;
    private String city;
    private String state;
    private String zip;
    private int phone;

}
