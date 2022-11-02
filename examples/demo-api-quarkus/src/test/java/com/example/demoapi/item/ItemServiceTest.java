package com.example.demoapi.item;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;

@QuarkusTest
class ItemServiceTest {

    @Inject
    ItemService itemService;

    @BeforeEach
    void init() {
        itemService.get().forEach(v -> itemService.delete(v.getId()));
    }

    @Test
    void testCreate() {
        Item item = new Item();
        item.setName("n1");
        item = itemService.create(item);
        Assertions.assertNotNull(item.getId());
    }

    @Test
    void testGetAll() {
        int n = 3;
        for (int i = 0; i < n; i++) {
            Item item = new Item();
            item.setName("n"+ i);
            itemService.create(item);
        }
        List<Item> items = itemService.get();
        Assertions.assertEquals(n, items.size());
    }

    @Test
    void testGet() {
        String name = "n1";
        Item item = new Item();
        item.setName(name);
        item = itemService.create(item);
        item = itemService.get(item.getId());
        Assertions.assertEquals(name, item.getName());
    }

    @Test
    void testDelete() {
        String name = "n1";
        Item item = new Item();
        item.setName(name);
        item = itemService.create(item);
        Item deleted = itemService.delete(item.getId());
        Assertions.assertEquals(name, deleted.getName());
        Long deletedId = deleted.getId();
        Assertions.assertThrows(NoResultException.class, () -> itemService.get(deletedId));
    }

    @Test
    void testUpdate() {
        String name = "n1";
        Item item = new Item();
        item.setName(name);
        item = itemService.create(item);
        String udpatedName = "n2";
        item.setName(udpatedName);
        Item updatedDto = itemService.update(item);
        Assertions.assertEquals(udpatedName, updatedDto.getName());
        Assertions.assertThrows(NoResultException.class, () -> itemService.findByName(name));
    }
}
