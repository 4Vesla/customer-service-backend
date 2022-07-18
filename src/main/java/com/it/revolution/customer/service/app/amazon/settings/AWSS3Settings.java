package com.it.revolution.customer.service.app.amazon.settings;

import com.it.revolution.customer.service.app.common.AbstractSettings;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "amazon.s3")
public class AWSS3Settings extends AbstractSettings {

    @NotNull
    private Bucket bucket;

}
