package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
  List<Item> findByOrderByNameAsc();

  List<Item> findByIdIn(List<Long> ids);
}
