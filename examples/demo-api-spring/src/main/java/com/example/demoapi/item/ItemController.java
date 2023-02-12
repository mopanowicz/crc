package com.example.demoapi.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

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
    public ResponseEntity<Item> create(@RequestBody Item item) {
        try {
            return new ResponseEntity<>(itemService.create(item), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            log.error("create failed", e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            log.error("create failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    ResponseEntity<Iterable<Item>> get() {
        return new ResponseEntity<>(itemService.get(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Item> get(@PathVariable Long id) {
        try {
            Item item = itemService.get(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (NoResultException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("get failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Item> update(@RequestBody Item item) {
        try {
            return new ResponseEntity<>(itemService.update(item), HttpStatus.OK);
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
