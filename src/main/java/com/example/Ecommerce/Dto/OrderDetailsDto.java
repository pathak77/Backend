package com.example.Ecommerce.Dto;

import com.example.Ecommerce.Entity.Address;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsDto {

    private UUID id;
    private Date orderDate;
    private Address address;
    private Double totalAmount;
    private String shipmentNumber;
    private Date expectedDeliveryDate;
    private List<OrderItemDetailDto> orderItemList;

}