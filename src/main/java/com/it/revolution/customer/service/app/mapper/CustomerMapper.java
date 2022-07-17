package com.it.revolution.customer.service.app.mapper;

import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import com.it.revolution.customer.service.app.model.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {

    public List<CustomerDto> mapToDtos(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToDto)
                .toList();
    }

    public CustomerDto mapToDto(Customer model) {
        CustomerDto dto = new CustomerDto();
        dto.setEmail(model.getEmail());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setBirthDate(model.getBirthDate());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setPhotoUrl(model.getPhotoUrl());
        return dto;
    }

}
