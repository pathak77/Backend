package com.example.Ecommerce.Dto;


import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {


    private UUID product_id;
    private String product_name;
    private Float product_price;
    private String product_image;
    private String product_description;
    private int product_quantity;
    private float discount_percentage;
    private int discount_amount;
    private String slug;
    private boolean isNewArrival;
    private Float rating;


    private String categoryName;
    private UUID categoryId;
    private UUID categoryTypeId;
    private String categoryTypeName;

    private List<ProductTypeDto> types;
    private List<ProductResourceDto> productResources;
}