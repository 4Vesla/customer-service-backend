package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.exception.TakenEmailException;
import com.it.revolution.customer.service.app.model.dto.RegistrationResponseDto;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound(NotFoundException e) {
        log.warn(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TakenEmailException.class)
    public ResponseEntity<RegistrationResponseDto> handleTakeEmail(TakenEmailException e) {
        return ResponseEntity.badRequest().body(
                    RegistrationResponseDto.builder()
                    .message(e.getMessage()).build()
            );
    }

}
