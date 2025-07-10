package com.example.Ecommerce.Repo;



import com.example.Ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface  ProductRepo extends JpaRepository<Product, UUID> {
    Product findBySlug(String slug);
}
