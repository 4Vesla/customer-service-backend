package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.mapper.CustomerMapper;
import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.service.CustomerService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomersController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> findAll(@RequestParam(required = false) Integer cursor,
                                                     @RequestParam(required = false) Integer limit) {
        List<Customer> customers = customerService.findCustomers(cursor, limit);
        List<CustomerDto> result = customerMapper.entityToDto(customers);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Long id) {
        Optional<CustomerDto> customerDtoOpt = customerService.findById(id)
                .map(customerMapper::entityToDto);
        return ResponseEntity.of(customerDtoOpt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        customerService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id,
                                              @RequestBody CustomerDto customerDto) throws NotFoundException {
        Customer entity = customerMapper.dtoToEntity(customerDto);
        entity.setId(id);

        Customer persisted = customerService.update(entity);
        return ResponseEntity.ok(customerMapper.entityToDto(persisted));
    }

}
