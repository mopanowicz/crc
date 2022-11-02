package com.example.demoapi.item;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByOrderByNameAsc();

    List<Item> findByIdIn(List<Long> ids);

    Optional<Item> findByName(String name);
}
