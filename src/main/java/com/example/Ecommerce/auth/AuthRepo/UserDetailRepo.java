package com.example.Ecommerce.auth.AuthRepo;

import com.example.Ecommerce.auth.AuthticationEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserDetailRepo extends JpaRepository<User, UUID> {

    User findByEmail(String username);
}
