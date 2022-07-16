package com.it.revolution.customer.service.app.service;

import com.it.revolution.customer.service.app.entity.Customer;
import com.it.revolution.customer.service.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByName(username);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean existsByUsername(String username) {
        return customerRepository.existsByName(username);
    }

}
