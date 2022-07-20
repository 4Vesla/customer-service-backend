package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.common.ControllerUtil;
import com.it.revolution.customer.service.app.exception.BadAuthorizedCredentialsException;
import com.it.revolution.customer.service.app.exception.TakenEmailException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException e) {
        return ControllerUtil.getErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(TakenEmailException.class)
    public ResponseEntity<Map<String, String>> handleTakeEmail(TakenEmailException e) {
        return ControllerUtil.getErrorResponse(e, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException e) {
        return ControllerUtil.getErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadAuthorizedCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadAuthorizedCredentials(BadAuthorizedCredentialsException e) {
       return ControllerUtil.getErrorResponse(e, HttpStatus.FORBIDDEN);
    }

}
