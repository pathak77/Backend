package com.example.Ecommerce.auth.Verification;

import java.util.Random;

public class VerificationCodeGenerator {

    public static String generateCode(){
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
