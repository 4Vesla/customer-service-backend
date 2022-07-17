package com.it.revolution.customer.service.app.model.dto;

import com.it.revolution.customer.service.app.model.entity.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponseDto {

    private String message;

    private Customer registered;

}
