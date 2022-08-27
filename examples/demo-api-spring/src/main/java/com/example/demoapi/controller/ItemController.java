package com.example.demoapi.controller;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.dto.ItemDTO;
import com.example.demoapi.service.ItemService;

@RestController
@CrossOrigin
@RequestMapping(ItemController.API_ROOT)
public class ItemController {

  static final String API_ROOT = "/v1/items"; 
  static final Logger log = LoggerFactory.getLogger(ItemController.class);

  final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping
  public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO dto) {
    try {
      return new ResponseEntity<>(itemService.create(dto), HttpStatus.CREATED);
    } catch (DataIntegrityViolationException e) {
      log.error("create failed", e);
      return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (Exception e) {
      log.error("create failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  ResponseEntity<Iterable<ItemDTO>> get() {
    return new ResponseEntity<>(itemService.get(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  ResponseEntity<ItemDTO> get(@PathVariable Long id) {
    try {
      ItemDTO dto = itemService.get(id);
      return new ResponseEntity<>(dto, HttpStatus.OK);
    } catch (NoResultException e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      log.error("get failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping
  public ResponseEntity<ItemDTO> update(@RequestBody ItemDTO dto) {
    try {
      return new ResponseEntity<>(itemService.update(dto), HttpStatus.OK);
    } catch (NoResultException e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      log.error("update failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    try {
      itemService.delete(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (NoResultException e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      log.error("delete failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
