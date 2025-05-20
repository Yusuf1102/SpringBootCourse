package com.week2.springbootwebtutorial.springbootwebtutorial.exceptions;

public class ResourceNotFoundException  extends  RuntimeException{
    public ResourceNotFoundException (String message){
            super(message);
    }
}
