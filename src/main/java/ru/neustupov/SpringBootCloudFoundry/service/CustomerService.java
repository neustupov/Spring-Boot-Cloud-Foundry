package ru.neustupov.SpringBootCloudFoundry.service;

import java.util.Collection;
import org.springframework.stereotype.Component;
import ru.neustupov.SpringBootCloudFoundry.beans.Customer;
import ru.neustupov.SpringBootCloudFoundry.data.CustomerRepository;

@Component
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Collection<Customer> findAll() {
    return this.customerRepository.findAll();
  }
}
