package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.AddressDto;
import com.example.Ecommerce.Entity.Address;
import com.example.Ecommerce.Repo.AddressRepo;
import com.example.Ecommerce.auth.AuthticationEntities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;

import java.util.UUID;

@Service
public class AddressServiceImpl {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AddressRepo addressRepository;

    public Address createAddress(AddressDto addressRequest, Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        Address address = Address.builder()
                .address_id(addressRequest.getAddress_id())
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .zip(addressRequest.getZip())
                .phone(addressRequest.getPhone())
                .user(user)
                .build();
        return addressRepository.save(address);
    }

    public void deleteAddress(UUID id) {
        addressRepository.deleteById(id);
    }
}