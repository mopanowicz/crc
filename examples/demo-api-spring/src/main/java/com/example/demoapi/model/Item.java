package com.example.demoapi.model;

import javax.persistence.*;

@Entity
@Table(
        name = "ITEM",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;

    @Version
    Integer entityVersion;

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

    public Integer getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Integer entityVersion) {
        this.entityVersion = entityVersion;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", entityVersion=" + entityVersion + "]";
    }
}
