package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.exception.TakenEmailException;
import com.it.revolution.customer.service.app.mapper.CustomerMapper;
import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.dto.RegistrationResponseDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<RegistrationResponseDto> register(CustomerDto newCustomer, @RequestParam("password") String password, @RequestParam(name = "photo", required = false) MultipartFile photo) throws TakenEmailException, IllegalStateException {

        Customer registered = customerService.register(newCustomer, password, photo);
        CustomerDto customerDto = customerMapper.entityToDto(registered);

        return ResponseEntity.ok(RegistrationResponseDto.builder().message("Registration succeed!").registered(customerDto).build());
    }

    @GetMapping("/activate/{id}/{activationCode}")
    public String activate(@PathVariable(name = "id") Long id, @PathVariable(name = "activationCode") String code) {
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
