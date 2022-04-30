package com.example.demoapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demoapi.dto.ItemDTO;
import com.example.demoapi.model.Item;

@ApplicationScoped
public class ItemService {

  static final Logger log = LoggerFactory.getLogger(ItemService.class);

  @Inject
  EntityManager em;

  @Transactional
  public ItemDTO create(ItemDTO dto) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("create dto=%s", dto));
    }
    Item item = from(dto);
    em.persist(item);
    return from(item);
  }

  @Transactional
  public List<ItemDTO> get() {
    if (log.isDebugEnabled()) {
      log.debug("get all");
    }
    List<ItemDTO> dtos = new ArrayList<>();
    TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item ORDER BY item.name", Item.class);
    query.getResultStream().forEach(v -> dtos.add(from(v)));
    return dtos;
  }

  @Transactional
  public ItemDTO get(Long id) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("get it=%s", id));
    }
    Item item = em.find(Item.class, id);
    if (item != null) {
      return from(item);
    } else {
      throw new NotFoundException("ItemDTO id=" + id + " not found");
    }
  }

  @Transactional
  public List<ItemDTO> get(List<Long> ids) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("get ids=%s", ids));
    }
    List<ItemDTO> dtos = new ArrayList<>();
    TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item WHERE item.id IN (:ids) ORDER BY name", Item.class);
    query.setParameter("ids", ids);
    query.getResultStream().forEach(v -> dtos.add(from(v)));
    return dtos;
  }

  @Transactional
  public ItemDTO update(ItemDTO dto) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("update dto=%s", dto));
    }
    Item item = from(dto);
    em.persist(item);
    return from(item);
  }

  @Transactional
  public ItemDTO delete(Long id) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("delete id=%s", id));
    }
    Item item = em.find(Item.class, id);
    em.remove(item);
    return from(item);
  }

  Item from(ItemDTO dto) {
    Item entity = Item.create();
    entity.setId(dto.getId());
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
