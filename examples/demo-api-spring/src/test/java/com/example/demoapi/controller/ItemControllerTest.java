package com.example.demoapi.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demoapi.dto.ItemDTO;
import com.example.demoapi.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ItemControllerTest {

  static final String baseURL = "http://localhost:8181";

  @Autowired
  ItemService itemService;

  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  void init() {
    itemService.get().forEach(v -> itemService.delete(v.getId()));
  }

  @Test
  void testCreate() throws JsonProcessingException {
    String name = "n1";

    ItemDTO dto = ItemDTO.create();
    dto.setName(name);

    given()
      .when()
        .body(objectMapper.writeValueAsString(dto))
          .contentType(ContentType.JSON)
          .post(baseURL + "/v1/items")
      .then()
        .statusCode(201);
  }

  @Test
  void testGetAll() {
    given()
      .when().get(baseURL + "/v1/items")
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

    given()
      .when().get(baseURL + "/v1/items/"+ dto.getId())
      .then()
        .statusCode(200)
        .body("name", equalTo(name));
  }
}
