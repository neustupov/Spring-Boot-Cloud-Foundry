package ru.neustupov.SpringBootCloudFoundry.controller;

import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neustupov.SpringBootCloudFoundry.beans.Customer;
import ru.neustupov.SpringBootCloudFoundry.service.CustomerService;

@RestController
public class CustomerRestController {

  private final CustomerService customerService;

  public CustomerRestController(
      CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customers")
  public Collection<Customer> readAll() {
    return this.customerService.findAll();
  }
}
