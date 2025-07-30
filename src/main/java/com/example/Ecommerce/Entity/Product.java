package com.example.Ecommerce.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product{


   @GeneratedValue(strategy = GenerationType.AUTO)
   @Id
   public UUID product_id;

   @Column(nullable = false)
   private String product_name;

   @Column(nullable = false)
   private Float product_price;

   @Column(nullable = false)
   private  String product_image;

   private String product_description;

   private int product_quantity;

   private float discount_percentage;

   private float rating;

   private int discount_amount;

   @Column(nullable = false,unique = true)
   private String slug;

   @Column(nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private java.util.Date createdAt;

   @Column(nullable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private java.util.Date updatedAt;

   private boolean isNewArrival;

   @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
   private List<ProductType> productType;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id",nullable = false)
   @JsonIgnore
   private Category category;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "categoryType_id",nullable = false)
   @JsonIgnore
   private CategoryType categoryType;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   private List<Resources> resources;

   @PrePersist
   protected void onCreate() {
      createdAt = new java.util.Date();
      updatedAt = createdAt;
   }

   @PreUpdate
   protected void onUpdate() {
      updatedAt = new java.util.Date();
   }
}
