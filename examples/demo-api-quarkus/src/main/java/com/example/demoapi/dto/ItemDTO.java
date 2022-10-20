package com.example.demoapi.dto;

public class ItemDTO {

    Long id;
    String name;
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
        return "ItemDTO [id=" + id + ", name=" + name + ", entityVersion=" + entityVersion + "]";
    }
}
