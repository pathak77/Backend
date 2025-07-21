package com.example.Ecommerce.auth.AuthRepo;

import com.example.Ecommerce.auth.AuthticationEntities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, UUID> {
    Authority findByRoleCode(String user);
}