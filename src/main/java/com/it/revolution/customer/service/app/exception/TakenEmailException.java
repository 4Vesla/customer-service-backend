package com.it.revolution.customer.service.app.exception;

import lombok.Getter;

@Getter
public class TakenEmailException extends Exception{

    private final String email;

    public TakenEmailException(String email){
        this(email, "Customer with email " + email + " is already in use, try another one.");
    }

    public TakenEmailException(String email, String message){
        super(message);
        this.email = email;
    }

}
