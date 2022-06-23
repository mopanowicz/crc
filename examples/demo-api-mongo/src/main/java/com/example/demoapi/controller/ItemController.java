package com.example.demoapi.controller;

import com.example.demoapi.model.Item;
import com.example.demoapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(ItemController.API_ROOT)
@RequiredArgsConstructor
@Slf4j
public class ItemController {

  static final String API_ROOT = "/v1/items"; 

  final ItemRepository itemRepository;

  @PostMapping
  public ResponseEntity<Item> create(@RequestBody Item dto) {
    try {
      return new ResponseEntity<>(itemRepository.insert(dto), HttpStatus.CREATED);
    } catch (DataIntegrityViolationException e) {
      log.error("create failed", e);
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (Exception e) {
      log.error("create failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  ResponseEntity<Iterable<Item>> get() {
    return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  ResponseEntity<Item> get(@PathVariable String id) {
    try {
      Optional<Item> item = itemRepository.findById(id);
      if (item.isPresent())
        return new ResponseEntity<>(item.get(), HttpStatus.OK);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      log.error("get failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping
  public ResponseEntity<Item> update(@RequestBody Item item) {
    try {
      return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
    } catch (Exception e) {
      log.error("update failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") String id) {
    try {
      itemRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      log.error("delete failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
