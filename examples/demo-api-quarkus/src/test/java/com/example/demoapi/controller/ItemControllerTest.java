package com.example.demoapi.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ItemControllerTest {

  @Test
  void testGetAll() {
    given()
      .when().get("/v1/items")
      .then()
        .statusCode(200)
        .body(is("[]"));
  }
}
