package com.it.revolution.customer.service.app.controllers;

import com.it.revolution.customer.service.app.model.entity.Customer;
import com.it.revolution.customer.service.app.service.CustomerService;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/activate")
public class EmailActivationController {

    @Value("${web.host.frontend}")
    private String frontedHost;

    private final CustomerService customerService;

    @Autowired
    public EmailActivationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/email/{id}/{activationCode}")
    public RedirectView activate(@PathVariable(name = "id") Long id,
                                 @PathVariable(name = "activationCode") String code) throws URISyntaxException {
        Customer customer = customerService.findById(id).orElse(null);
        if (Objects.nonNull(customer) && customer.getActivationCode().equals(code)) {
            customer.setActivationCode("");
            customer = customerService.save(customer);
        }
        Optional<Customer> customerOptional = Optional.ofNullable(customer);
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost(frontedHost)
                .setPath("/login")
                .addParameter("emailConfirm", String.valueOf(customerOptional.map(Customer::isActivated).orElse(false)))
                .addParameter("email", customerOptional.map(Customer::getEmail).orElse("")).build();
        return new RedirectView(uri.toString());
    }

}
