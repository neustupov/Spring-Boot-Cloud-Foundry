package ru.neustupov.SpringBootCloudFoundry.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cat {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  public Cat() {
  }

  public Cat(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Cat{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
