package com.it.revolution.customer.service.app.amazon.settings;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Bucket {

    @NotNull
    private String name;

}
