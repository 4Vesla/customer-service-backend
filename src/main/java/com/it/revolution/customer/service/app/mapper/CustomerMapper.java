package com.it.revolution.customer.service.app.mapper;

import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {

    public List<CustomerDto> entityToDto(List<Customer> customers) {
        return customers.stream()
                .map(this::entityToDto)
                .toList();
    }

    public CustomerDto entityToDto(Customer model) {
        CustomerDto dto = new CustomerDto();
        dto.setId(model.getId());
        dto.setEmail(model.getEmail());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setBirthDate(model.getBirthDate());
        dto.setPhoneNumber(model.getPhoneNumber());
        return dto;
    }

    public Customer dtoToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setEmail(dto.getEmail());
        customer.setBirthDate(dto.getBirthDate());
        customer.setPhoneNumber(dto.getPhoneNumber());
        return customer;
    }

}
