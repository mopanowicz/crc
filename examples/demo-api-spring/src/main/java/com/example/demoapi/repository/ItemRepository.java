package com.example.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demoapi.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
  List<Item> findByOrderByNameAsc();

  List<Item> findByIdIn(List<Long> ids);

  Optional<Item> findByName(String name);
}
