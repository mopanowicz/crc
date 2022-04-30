package com.example.demoapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demoapi.dto.ItemDTO;
import com.example.demoapi.model.Item;
import com.example.demoapi.repository.ItemRepository;

@Service
public class ItemService {

  static final Logger log = LoggerFactory.getLogger(ItemService.class);

  final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public ItemDTO create(ItemDTO dto) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("create dto=%s", dto));
    }
    Item item = from(dto);
    return from(itemRepository.save(item));
  }

  public List<ItemDTO> get() {
    if (log.isDebugEnabled()) {
      log.debug("get");
    }
    List<ItemDTO> ps = new ArrayList<>();
    for (Item item : itemRepository.findByOrderByNameAsc()) {
      ps.add(from(item));
    }
    return ps;
  }

  public ItemDTO get(Long id) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("get it=%s", id));
    }
    Optional<Item> item = itemRepository.findById(id);
    if (item.isPresent()) {
      return from(item.get());
    } else {
      throw new NotFoundException("ItemDTO id=" + id + " not found");
    }
  }

  public List<ItemDTO> get(List<Long> ids) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("get ids=%s", ids));
    }
    List<ItemDTO> dtos = new ArrayList<>();
    for (Item item : itemRepository.findByIdIn(ids)) {
      dtos.add(from(item));
    }
    return dtos;
  }

  public ItemDTO update(ItemDTO dto) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("update dto=%s", dto));
    }
    Item item = from(dto);
    return from(itemRepository.save(item));
  }

  public ItemDTO delete(Long id) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("delete id=%s", id));
    }
    ItemDTO dto = get(id);
    itemRepository.deleteById(id);
    return dto;
  }

  Item from(ItemDTO dto) {
    Item entity = null;
    if (dto.getId() != null) {
      Optional<Item> o = itemRepository.findById(dto.getId());
      if (o.isPresent()) {
        entity = o.get();
      } else {
        throw new NotFoundException("Item id=" + dto.getId() + " not found");
      }
    } else {
      entity = Item.create();
    }
    entity.setName(dto.getName());
    entity.setVersion(dto.getVersion());
    return entity;
  }

  ItemDTO from(Item entity) {
    ItemDTO dto = ItemDTO.create();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setVersion(entity.getVersion());
    return dto;
  }
}
