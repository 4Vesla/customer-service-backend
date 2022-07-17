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

    @GetMapping("/activate/{id}/{activationCode}")
    public String activate(@PathVariable(name = "id") Long id,
                           @PathVariable(name = "activationCode") String code) {
        Customer customer = customerService.findById(id).orElse(null);
        if (Objects.nonNull(customer) && customer.getActivationCode().equals(code)) {
            customer.setActivationCode("");
            customerService.save(customer);
            //TODO redirect to front endpoint
            return "Your account has been activated successfully! Visit your home page.";
        }
        return "Ooops, something went wrong.";
    }

}
