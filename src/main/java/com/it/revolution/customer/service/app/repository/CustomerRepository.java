package com.it.revolution.customer.service.app.repository;

import com.it.revolution.customer.service.app.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    public Optional<Customer> findByName(String name);
    public boolean existsByName(String name);
}
