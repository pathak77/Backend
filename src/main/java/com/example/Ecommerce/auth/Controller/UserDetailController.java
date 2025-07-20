package com.example.Ecommerce.auth.Controller;



import com.example.Ecommerce.auth.AuthticationEntities.User;
import com.example.Ecommerce.auth.Dto.UserDataDto;
import com.example.Ecommerce.auth.Services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserDetailController {

    @Autowired
    private UserDetailService UserDetailService;

    @GetMapping("/profile")
    public ResponseEntity<UserDataDto> getUserProfile(Principal principal){
        User user = (User) UserDetailService.loadUserByUsername(principal.getName());

        if(  user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserDataDto UserDataDto = new UserDataDto().builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .id(user.getId())
                .phoneNumber(user.getPhone())
                .addressList(user.getAddressList())
                .authorityList(user.getAuthorities().toArray()).build();

        return new ResponseEntity<>(UserDataDto, HttpStatus.OK);

    }
}
