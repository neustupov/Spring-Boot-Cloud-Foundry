package ru.neustupov.SpringBootCloudFoundry;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

  @Bean
  DataSource dataSource(){
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .setName("customers").build();
  }
}
