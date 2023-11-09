package com.example.demoapi.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ItemControllerTest {

    @Inject
    ItemService itemService;

    @Inject
    ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        String name = "n1";

        Item item = new Item();
        item.setName(name);

        try {
            given()
                    .when()
                    .body(objectMapper.writeValueAsString(item))
                    .contentType(ContentType.JSON)
                    .post("/v1/items")
                    .then()
                    .statusCode(201);
        } finally {
            item = itemService.findByName(name);
            itemService.delete(item.getId());
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

        Item item = new Item();
        item.setName(name);
        item = itemService.create(item);

        try {
            given()
                    .when().get("/v1/items/" + item.getId())
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(name));
        } finally {
            item = itemService.findByName(name);
            itemService.delete(item.getId());
        }
    }
}
