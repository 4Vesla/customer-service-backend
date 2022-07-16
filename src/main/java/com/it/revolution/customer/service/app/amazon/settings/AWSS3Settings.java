package com.it.revolution.customer.service.app.amazon.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties(prefix = "amazon.s3")
public class AWSS3Settings {

    @NotNull
    private Bucket bucket;

}
