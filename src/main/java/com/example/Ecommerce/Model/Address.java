package com.example.Ecommerce.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String username;

    private String street;
    private String city;
    private String state;
    private String zip;
    private int phone;

}
