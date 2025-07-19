package com.example.Ecommerce.auth.Services;

import com.example.Ecommerce.auth.AuthRepo.AuthorityRepo;
import com.example.Ecommerce.auth.AuthticationEntities.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepo authorityRepo;

    public List<Authority> getUserAuthority(){
       List<Authority> authorities=new ArrayList<>();
       Authority authority= authorityRepo.findByRoleCode("USER");
       authorities.add(authority);
       return  authorities;
    }

    public Authority createAuthority(String role, String description){
        Authority authority = Authority.builder()
                .role(role)
                .build();
        return authorityRepo.save(authority);
    }
}
