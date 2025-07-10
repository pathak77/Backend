package com.example.Ecommerce.Service;



import com.example.Ecommerce.Dto.AddressDto;
import com.example.Ecommerce.Model.Address;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(AddressDto addressDto);

    List<AddressDto> getAddresses();

    AddressDto getAddress(Long addressId);

    AddressDto updateAddress(Long addressId, Address address);

    String deleteAddress(Long addressId);
}
