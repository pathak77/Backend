package com.example.Ecommerce.Exceptions;

public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(String message){
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public CategoryNotFoundException(){
        super("Category Not Found");
    }
}
