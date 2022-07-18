package com.it.revolution.customer.service.app.common.settings;

import com.it.revolution.customer.service.app.common.AbstractSettings;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Data
@Configuration
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "web")
public class WebSettings extends AbstractSettings {

    @NotNull
    private Backend backend;

    @NotNull
    private Frontend frontend;

}
