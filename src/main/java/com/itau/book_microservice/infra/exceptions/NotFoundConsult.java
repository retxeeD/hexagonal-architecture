package com.itau.book_microservice.infra.exceptions;

public class NotFoundConsult extends RuntimeException{

    public NotFoundConsult(String message){
        super(message);
    }
}
