package com.example.Ecommerce.Repo;

import com.example.Ecommerce.Entity.Order;
import com.example.Ecommerce.auth.AuthticationEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, UUID> {

    List<Order> findByUser(User user);
}