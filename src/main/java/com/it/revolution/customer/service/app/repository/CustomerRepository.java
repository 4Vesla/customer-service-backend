package com.it.revolution.customer.service.app.repository;

import com.it.revolution.customer.service.app.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
    boolean existsByName(String name);
}
