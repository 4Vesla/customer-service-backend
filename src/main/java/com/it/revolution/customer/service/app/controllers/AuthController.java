package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.exception.BadAuthorizedCredentialsException;
import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.security.jwt.JwtProvider;
import com.it.revolution.customer.service.app.security.jwt.dto.AuthRequest;
import com.it.revolution.customer.service.app.security.jwt.dto.AuthResponse;
import com.it.revolution.customer.service.app.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final CustomerService customerService;

    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public AuthResponse auth(AuthRequest request) throws BadAuthorizedCredentialsException {
        Customer customer = customerService.findByEmailAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(customer.getEmail());
        return new AuthResponse(token);
    }

}
