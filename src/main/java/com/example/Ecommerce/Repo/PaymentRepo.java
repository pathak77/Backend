package com.example.Ecommerce.Repo;

import com.example.Ecommerce.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepo extends JpaRepository<Payment, UUID> {
}
