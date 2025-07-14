package com.example.Ecommerce.auth.Services;


import com.example.Ecommerce.auth.AuthRepo.UserDetailRepo;
import com.example.Ecommerce.auth.AuthticationEntities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailRepo userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userDetailRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found -- "+username);
        }
        return user;
    }
}
