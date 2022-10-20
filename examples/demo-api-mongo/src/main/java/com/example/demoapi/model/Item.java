package com.example.demoapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Item {

    @Id
    String id;

    String name;

    Integer entityVersion;
}
