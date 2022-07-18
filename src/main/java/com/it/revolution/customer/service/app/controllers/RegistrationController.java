package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.exception.TakenEmailException;
import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.dto.RegistrationResponseDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<RegistrationResponseDto> register(CustomerDto newCustomer,
                                                              @RequestParam("password") String password,
                                                              @RequestParam("photo") MultipartFile photo)
            throws TakenEmailException {

        Customer registered = customerService.register(newCustomer, password, photo);

        return ResponseEntity.ok(RegistrationResponseDto.builder()
                .message("Registration succeed!")
                .registered(registered).build()
        );
    }

}
