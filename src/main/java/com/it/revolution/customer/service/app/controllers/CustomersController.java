package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.mapper.CustomerMapper;
import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomersController {

    private final CustomerService customerService;
    private final CustomerMapper mapper;

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> findAll(@RequestParam(required = false) Integer cursor,
                                                     @RequestParam(required = false) Integer limit) {
        List<Customer> customers = customerService.findCustomers(cursor, limit);
        List<CustomerDto> result = mapper.mapToDtos(customers);
        return ResponseEntity.ok(result);
    }

}
