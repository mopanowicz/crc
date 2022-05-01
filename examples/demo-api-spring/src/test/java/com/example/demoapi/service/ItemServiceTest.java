package com.example.demoapi.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void init() {
    itemService.get().forEach(v -> itemService.delete(v.getId()));
  }

  @Test
  void testCreate() {
    ItemDTO dto = ItemDTO.create();
    dto.setName("n1");
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

  @Test
  void testGet() {
    String name = "n1";
    ItemDTO dto = ItemDTO.create();
    dto.setName(name);
    dto = itemService.create(dto);
    dto = itemService.get(dto.getId());
    Assertions.assertEquals(name, dto.getName());
  }

  @Test
  void testDelete() {
    String name = "n1";
    ItemDTO dto = ItemDTO.create();
    dto.setName(name);
    dto = itemService.create(dto);
    ItemDTO deleted = itemService.delete(dto.getId());
    Assertions.assertEquals(name, deleted.getName());
    Assertions.assertThrows(NoResultException.class, () -> { itemService.get(deleted.getId()); });
  }

  @Test
  void testUpdate() {
    String name = "n1";
    ItemDTO dto = ItemDTO.create();
    dto.setName(name);
    dto = itemService.create(dto);
    String udpatedName = "n2";
    dto.setName(udpatedName);
    ItemDTO updatedDto = itemService.update(dto);
    Assertions.assertEquals(udpatedName, updatedDto.getName());
    Assertions.assertThrows(NoResultException.class, () -> { itemService.findByName(name); });
  }
}
