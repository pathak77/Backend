package com.example.Ecommerce.auth.Controller;

import com.example.Ecommerce.auth.AuthticationEntities.User;
import com.example.Ecommerce.auth.Dto.LoginRequest;
import com.example.Ecommerce.auth.Dto.RegistrationRequest;
import com.example.Ecommerce.auth.Dto.UserToken;
import com.example.Ecommerce.auth.JwtConfig.JWTTokenHelper;
import com.example.Ecommerce.auth.Services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.Ecommerce.auth.Services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    UserDetailService userDetailsService;

    @Autowired
    JWTTokenHelper jwtTokenHelper;


    @PostMapping("/login")
    public ResponseEntity<UserToken> login(@RequestBody LoginRequest loginRequest){

        System.out.println("Login request: " + loginRequest + " has been added");
        try{
            Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUserName(),
                    loginRequest.getPassword());

            Authentication authenticationResponse = this.authenticationManager.authenticate(authentication);

            if(authenticationResponse.isAuthenticated()){
                User user= (User) authenticationResponse.getPrincipal();
                if(!user.isEnabled()) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }

                String token = jwtTokenHelper.generateToken(user.getEmail());
                UserToken userToken= UserToken.builder().token(token).build();
                return new ResponseEntity<>(userToken,HttpStatus.OK);
            }

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request){

        System.out.println("register endpoint hit!!!!!!!!!!!!!!!!!!");
        String registrationResponse = registrationService.createUser(request);
        return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestParam (required = true, name= "name") String userName,
                                        @RequestParam (required = true, name= "code") String code){

        User user= (User) userDetailsService.loadUserByUsername(userName);

        if(user != null && user.getVerificationCode().equals(code)){
            registrationService.verifyUser(userName);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
