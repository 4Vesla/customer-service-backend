package com.it.revolution.customer.service.app.service;

import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.repository.CustomerPaginationRepository;
import com.it.revolution.customer.service.app.repository.CustomerRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final static Integer PAGE_SIZE = 10;

    private final CustomerRepository customerRepository;
    private final CustomerPaginationRepository customerPaginationRepository;

    private final PasswordEncoder passwordEncoder;

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByUsername(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer findByEmailAndPassword(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c->passwordEncoder.matches(password, c.getPassword()))
                .orElse(null);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Long id) throws NotFoundException {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("Customer with id = %s was not found", id));
        }
        customerRepository.delete(Customer.of(id));
    }

    public boolean existsByUsername(String username) {
        return customerRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUsername(s).orElse(null);
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

    public Customer update(Customer entity) throws NotFoundException {
        Optional<Customer> customerOpt = customerRepository.findById(entity.getId());
        if (customerOpt.isPresent()) {
            Customer result = mergeFields(customerOpt.get(), entity);
            return customerRepository.save(result);
        } else {
            throw new NotFoundException(String.format("Customer with id = %s was not found", entity.getId()));
        }
    }

    private Customer mergeFields(Customer updatable, Customer customer) {
        Optional.ofNullable(customer.getName())
                .ifPresent(updatable::setName);
        Optional.ofNullable(customer.getSurname())
                .ifPresent(updatable::setSurname);
        Optional.ofNullable(customer.getEmail())
                .ifPresent(updatable::setEmail);
        Optional.ofNullable(customer.getPhoneNumber())
                .ifPresent(updatable::setPhoneNumber);
        Optional.ofNullable(customer.getBirthDate())
                .ifPresent(updatable::setBirthDate);
        return updatable;
    }

}
