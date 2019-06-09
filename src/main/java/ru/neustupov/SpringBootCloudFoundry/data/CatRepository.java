package ru.neustupov.SpringBootCloudFoundry.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.neustupov.SpringBootCloudFoundry.beans.Cat;

@RepositoryRestResource
public interface CatRepository extends JpaRepository<Cat, Long> {

}
