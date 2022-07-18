package com.it.revolution.customer.service.app.service;

import com.it.revolution.customer.service.app.model.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ValidationService {

    public IllegalStateException validateCustomerDto(CustomerDto customerDto) {
        if (isNull(customerDto)) {
            return new IllegalStateException("Customer object can not be null");
        }
        if (isNull(customerDto.getName())) {
            return new IllegalStateException("Customer name can not be null");
        }
        if ("".equals(customerDto.getName())) {
            return new IllegalStateException("Customer name can not be empty");
        }
        if (isNull(customerDto.getEmail())) {
            return new IllegalStateException("Customer email can not be null");
        }
        if ("".equals(customerDto.getEmail())) {
            return new IllegalStateException("Customer email can not be empty");
        }

        return validateEmail(customerDto.getEmail());
    }

    public IllegalStateException validateEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return new IllegalStateException("Not valid email");
        }
        return null;
    }

}
