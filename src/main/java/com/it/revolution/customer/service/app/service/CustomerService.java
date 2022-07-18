package com.it.revolution.customer.service.app.service;

import com.it.revolution.customer.service.app.amazon.service.FileService;
import com.it.revolution.customer.service.app.exception.TakenEmailException;
import com.it.revolution.customer.service.app.mapper.CustomerMapper;
import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.model.entity.Role;
import com.it.revolution.customer.service.app.repository.CustomerPaginationRepository;
import com.it.revolution.customer.service.app.repository.CustomerRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    @Value("${web.host.backend}")
    private String endpointUrl;

    private final static Integer PAGE_SIZE = 10;

    private final CustomerRepository customerRepository;
    private final CustomerPaginationRepository customerPaginationRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSender;
    private final FileService fileService;
    private final CustomerMapper customerMapper;
    private final ValidationService validationService;

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
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new NotFoundException(String.format("Customer with id = %s was not found", id));
        }
        customer.ifPresent(c->fileService.deleteFile(c.getPhotoUrl()));
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

    public Customer register(CustomerDto customerDto, String password, MultipartFile photo) throws TakenEmailException, IllegalStateException {
        IllegalStateException exception = validationService.validateCustomerDto(customerDto);
        if (nonNull(exception)) {
            throw exception;
        }

        Customer customer = customerMapper.dtoToEntity(customerDto);
        if (existsByUsername(customer.getEmail())){
            throw new TakenEmailException(customer.getEmail());
        }
        customer.setPassword(passwordEncoder.encode(password));
        customer.setActivationCode(UUID.randomUUID().toString());
        String photoUrl = uploadPhoto(photo);
        customer.setPhotoUrl(photoUrl);
        customer.setRoles(Set.of(Role.USER));
        Customer registered = customerRepository.save(customer);
        sendMessage(registered);
        return registered;
    }

    private String uploadPhoto(MultipartFile photo) {
        if (isNull(photo)) {
            return null;
        }

        return fileService.uploadFile(photo);
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

    /**
     * Sends email message with activation code to the registered Customer.<br>
     * */
    private void sendMessage(Customer customer) {
        String email = customer.getEmail();
        if (Objects.nonNull(email) && !email.isBlank()) {
            String message = String.format(
                    "Hello, %s\nWelcome to the 4vesla site. You are trying to create new account, please follow this link http://%s/activate/email/%d/%s to accomplish registration process.",
                    customer.getName(),
                    endpointUrl,
                    customer.getId(),
                    customer.getActivationCode()
            );
            mailSender.send(email, "4vesla account confirmation.", message);
        }
    }

}
