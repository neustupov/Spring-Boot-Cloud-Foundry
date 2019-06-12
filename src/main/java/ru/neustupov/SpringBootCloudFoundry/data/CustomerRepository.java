package ru.neustupov.SpringBootCloudFoundry.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.neustupov.SpringBootCloudFoundry.beans.Customer;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
