package com.example.demoapi.service;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demoapi.dto.ItemDTO;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ItemServiceTest {

  @Inject
  ItemService itemService;

  @Test
  void testCreate() {
    ItemDTO dto = itemService.create(new ItemDTO());
    Assertions.assertNotNull(dto);
  }
}
