package com.example.demoapi.item;

import jakarta.persistence.*;

@Entity
@Table(
        name = "ITEM",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@SequenceGenerator(name = Item.ID_GENERATOR, allocationSize = 1, sequenceName = Item.ID_GENERATOR_SEQUENCE)
public class Item {

    static final String ID_GENERATOR = "itemIdGenerator";
    static final String ID_GENERATOR_SEQUENCE = "item_id";

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    Long id;
    String name;
    @Version
    @Column(name = "entity_version")
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
