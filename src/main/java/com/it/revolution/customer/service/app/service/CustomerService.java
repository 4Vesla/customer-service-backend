package com.it.revolution.customer.service.app.service;

import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.repository.CustomerPaginationRepository;
import com.it.revolution.customer.service.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final static Integer PAGE_SIZE = 10;

    private final CustomerRepository customerRepository;
    private final CustomerPaginationRepository customerPaginationRepository;

    public Optional<Customer> findById(Long id) {
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

    public List<Customer> findCustomers(Integer cursor, Integer limit) {
        Pageable paging = getPageable(cursor, limit);
        Page<Customer> page = customerPaginationRepository.findAll(paging);

        return page.getContent();
    }

    private Pageable getPageable(Integer cursor, Integer limit) {
        int pageNumber = nonNull(cursor) ? cursor : 0;
        int pageSize = nonNull(limit) ? limit : PAGE_SIZE;
        return PageRequest.of(pageNumber, pageSize);
    }

}
