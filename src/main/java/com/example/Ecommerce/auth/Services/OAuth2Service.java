package com.example.Ecommerce.auth.Services;

import com.example.Ecommerce.auth.AuthRepo.UserDetailRepo;
import com.example.Ecommerce.auth.AuthticationEntities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2Service {

    @Autowired
    UserDetailRepo userDetailRepo;

    @Autowired
    private AuthorityService authorityService;

    public User getUser(String userName) {
        return userDetailRepo.findByEmail(userName);
    }

    public User createUser(OAuth2User oAuth2User, String provider) {
        String firstName = oAuth2User.getAttribute("given_name");
        String lastName = oAuth2User.getAttribute("family_name");
        String email = oAuth2User.getAttribute("email");
        User user= User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .enabled(true)
                .authorities(authorityService.getUserAuthority())
                .build();
        return userDetailRepo.save(user);
    }
}
