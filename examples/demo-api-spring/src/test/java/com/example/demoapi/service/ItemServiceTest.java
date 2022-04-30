package com.example.demoapi.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demoapi.dto.ItemDTO;

@SpringBootTest
@ActiveProfiles("test")
class ItemServiceTest {

  @Autowired
  ItemService itemService;

  @Test
  void testCreate() {
    ItemDTO dto = ItemDTO.create();
    dto.setName("n99");
    dto = itemService.create(dto);
    Assertions.assertNotNull(dto.getId());
  }

  @Test
  void testGetAll() {
    ItemDTO dto = ItemDTO.create();
    dto.setName("n3");
    itemService.create(dto);
    dto.setName("n2");
    itemService.create(dto);
    dto.setName("n1");
    itemService.create(dto);
    List<ItemDTO> dtos = itemService.get();
    Assertions.assertEquals("n1", dtos.get(0).getName());
  }
}
