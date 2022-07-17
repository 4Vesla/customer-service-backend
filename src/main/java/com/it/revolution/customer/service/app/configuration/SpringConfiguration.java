package com.it.revolution.customer.service.app.configuration;

import com.it.revolution.customer.service.app.amazon.settings.AWSS3Settings;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AWSS3Settings.class)
public class SpringConfiguration {
}
