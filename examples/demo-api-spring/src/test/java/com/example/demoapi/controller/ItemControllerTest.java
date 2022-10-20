package com.example.demoapi.controller;

import com.example.demoapi.dto.ItemDTO;
import com.example.demoapi.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ItemControllerTest {

    static final String apiRoot = "http://localhost:8181" + ItemController.API_ROOT;

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

        ItemDTO dto = new ItemDTO();
        dto.setName(name);

        given()
                .when()
                .body(objectMapper.writeValueAsString(dto))
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

        ItemDTO dto = new ItemDTO();
        dto.setName(name);
        dto = itemService.create(dto);

        given()
                .when().get(apiRoot + "/" + dto.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo(name));
    }
}
