package com.it.revolution.customer.service.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("birthDate")
    private LocalDate birthDate;

    @JsonProperty("photoUrl")
    private String photoUrl;
}
