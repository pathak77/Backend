package com.example.Ecommerce.Dto;

import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class AddressDto {

    private UUID address_id;

    private String username;

    private String street;
    private String city;
    private String state;
    private String zip;
    private int phone;
}
