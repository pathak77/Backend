package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.PaymentDto;
import java.net.http.HttpResponse;
import java.security.Principal;

public interface PaymentService {

    public HttpResponse addDetails(PaymentDto payment, Principal principal);
}
