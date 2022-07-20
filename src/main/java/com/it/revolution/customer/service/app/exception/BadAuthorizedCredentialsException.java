package com.it.revolution.customer.service.app.exception;

public class BadAuthorizedCredentialsException extends Exception {

    public BadAuthorizedCredentialsException(){
        super("Incorrect login, or password!");
    }

}
