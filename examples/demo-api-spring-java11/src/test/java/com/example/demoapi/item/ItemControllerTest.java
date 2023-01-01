package com.example.demoapi.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ItemControllerTest {

    static final String apiRoot = "http://localhost:8888" + ItemController.API_ROOT;

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

        Item item = new Item();
        item.setName(name);

        given()
                .when()
                .body(objectMapper.writeValueAsString(item))
                .contentType(ContentType.JSON)
                .post(apiRoot)
                .then()
                .statusCode(201);
    }

    @Test
    void testGetAll() {
        given()
                .when().get(apiRoot)
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

        given()
                .when().get(apiRoot + "/" + item.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo(name));
    }
}
