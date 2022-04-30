package com.example.demoapi.model;

import javax.persistence.*;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String name;

  @Version
  Integer version;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public static Item create() {
    return new Item();
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", name=" + name + ", version=" + version + "]";
  }
}
