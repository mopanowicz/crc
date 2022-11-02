package com.example.demoapi.service;

import com.example.demoapi.model.Item;
import com.example.demoapi.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    static final Logger log = LoggerFactory.getLogger(ItemService.class);

    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item create(Item item) {
        log.debug("create item={}", item);
        return itemRepository.save(item);
    }

    @Transactional
    public List<Item> get() {
        log.debug("get all");
        return itemRepository.findByOrderByNameAsc();
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    @Transactional
    public Item get(Long id) {
        log.debug("get id={}", id);
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new NoResultException("Item with id=" + id + " does not exist");
        }
    }

    @Transactional
    public List<Item> get(List<Long> ids) {
        log.debug("get ids={}", ids);
        return itemRepository.findByIdIn(ids);
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    @Transactional
    public Item findByName(String name) {
        log.debug("findByName name={}", name);
        Optional<Item> item = itemRepository.findByName(name);
        if (item.isPresent()) {
            return item.get();
        }
        throw new NoResultException("Item with name='" + name + "' does not exist");
    }

    @Transactional
    public Item update(Item item) {
        log.debug("update item={}", item);
        return itemRepository.save(item);
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    @Transactional
    public Item delete(Long id) {
        log.debug("delete id={}", id);
        Item item = get(id);
        itemRepository.deleteById(id);
        return item;
    }
}
