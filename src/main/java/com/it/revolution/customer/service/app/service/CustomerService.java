package com.it.revolution.customer.service.app.service;

import com.it.revolution.customer.service.app.entity.Customer;
import com.it.revolution.customer.service.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByUsername(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean existsByUsername(String username) {
        return customerRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUsername(s).orElse(null);
    }
}
