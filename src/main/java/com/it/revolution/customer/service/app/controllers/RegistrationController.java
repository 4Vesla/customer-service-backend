package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> register(CustomerDto newCustomer,
                                            @RequestParam("password") String password,
                                            @RequestParam("photo") MultipartFile photo) {
        Customer registered = customerService.register(newCustomer, password, photo);
        return ResponseEntity.ok(registered);
    }

    @GetMapping("/activate/{id}/{activationCode}")
    public ResponseEntity<Customer> activate(@PathVariable(name = "id") Long id,
                                             @PathVariable(name = "activationCode") String code) {
        Customer customer = customerService.findById(id).orElseThrow();
        if (customer.getActivationCode().equals(code)) {
            customer.setActivationCode("");
            customerService.save(customer);
            //TODO redirect to front endpoint
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.badRequest().body(customer);
    }

}
