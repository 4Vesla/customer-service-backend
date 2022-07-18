package com.it.revolution.customer.service.app.common.settings;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Frontend {

    @NotNull
    private String host;

}
