package com.example.Ecommerce.Exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ProductNotFoundException(){
        super("The Product You're Searching For ---> Not Found");
    }
}
