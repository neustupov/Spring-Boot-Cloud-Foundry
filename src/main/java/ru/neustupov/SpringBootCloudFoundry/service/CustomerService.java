package ru.neustupov.SpringBootCloudFoundry.service;

import java.util.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.neustupov.SpringBootCloudFoundry.beans.Customer;

@Component
public class CustomerService {

  private final JdbcTemplate jdbcTemplate;

  public CustomerService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Collection<Customer> findAll() {
    RowMapper<Customer> rowMapper =
        (rs, i) -> new Customer(rs.getLong("ID"), rs.getString("EMAIL"));
    return this.jdbcTemplate.query("SELECT * FROM CUSTOMERS", rowMapper);
  }
}
