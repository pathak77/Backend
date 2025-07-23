package com.example.Ecommerce.auth.Services;

import com.example.Ecommerce.auth.AuthRepo.UserDetailRepo;
import com.example.Ecommerce.auth.AuthticationEntities.User;
import com.example.Ecommerce.auth.Dto.RegistrationRequest;
import com.example.Ecommerce.auth.Verification.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

@Service
public class    RegistrationService {

    @Autowired
    private UserDetailRepo userDetailRepo;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public String createUser(RegistrationRequest request) {

        User  userExists = userDetailRepo.findByEmail(request.getEmail());

        if(userExists != null){
            return  "the account already exists!";
        }

        try{

            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setEnabled(false);
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            String code  = VerificationCodeGenerator.generateCode();

            user.setVerificationCode(code);
            user.setAuthorities(authorityService.getUserAuthority());
            userDetailRepo.save(user);

            System.out.println("Sending mail to user " + user.getEmail());
            String mailServiceOutcome = emailService.sendMail(user);

            System.out.println(mailServiceOutcome);

            return "User created! Please verify using provided mail id";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServerErrorException(e.getMessage(),e.getCause());
        }
    }

    public void verifyUser(String userName) {
        User user= userDetailRepo.findByEmail(userName);
        user.setEnabled(true);
        userDetailRepo.save(user);
    }
}
