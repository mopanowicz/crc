package com.example.demoapi.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.Test;

import com.example.demoapi.dto.ItemDTO;
import com.example.demoapi.service.ItemService;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class ItemControllerTest {

  @Inject
  ItemService itemService;
  
  @Test
  void testCreate() {
    String name = "n1";

    ItemDTO dto = ItemDTO.create();
    dto.setName(name);

    try {
      given()
        .when()
          .body(JsonbBuilder.create().toJson(dto))
            .contentType(ContentType.JSON)
            .post("/v1/items")
        .then()
          .statusCode(201);
    } finally {
      dto = itemService.findByName(name);
      itemService.delete(dto.getId());
    }
  }

  @Test
  void testGetAll() {
    given()
      .when().get("/v1/items")
      .then()
        .statusCode(200)
        .body(is("[]"));
  }

  @Test
  void testGet() {
    String name = "n1";

    ItemDTO dto = ItemDTO.create();
    dto.setName(name);
    dto = itemService.create(dto);

    try {
      given()
        .when().get("/v1/items/"+ dto.getId())
        .then()
          .statusCode(200)
          .body("name", equalTo(name));
    } finally {
      dto = itemService.findByName(name);
      itemService.delete(dto.getId());
    }
  }
}
