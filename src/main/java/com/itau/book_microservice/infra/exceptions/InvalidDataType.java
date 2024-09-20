package com.itau.book_microservice.infra.exceptions;

public class InvalidDataType extends RuntimeException{

    public InvalidDataType(String message){
        super(message);
    }

}
