package com.it.revolution.customer.service.app.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponseDto {

    private String message;

    private CustomerDto registered;

}
