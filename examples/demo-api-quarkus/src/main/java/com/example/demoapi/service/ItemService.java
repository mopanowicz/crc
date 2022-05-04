package com.example.demoapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

  /**
   * @throws NoResultException if the item does not exist
   */
  @Transactional
  public ItemDTO get(Long id) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("get id=%s", id));
    }
    Item item = em.find(Item.class, id);
    if (item != null) {
      return from(item);
    }
    throw new NoResultException("Item with id=" + id + " does not exist");
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

  /**
   * @throws NoResultException if the item does not exist
   */
  @Transactional
  public ItemDTO findByName(String name) {
    TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item WHERE item.name = :name", Item.class);
    query.setParameter("name", name);
    Item entity = query.getSingleResult();
    return from(entity);
  }

  @Transactional
  public ItemDTO update(ItemDTO dto) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("update dto=%s", dto));
    }
    Item item = from(dto);
    em.merge(item);
    return from(item);
  }

  /**
   * @throws NoResultException if the item does not exist
   */
  @Transactional
  public ItemDTO delete(Long id) {
    if (log.isDebugEnabled()) {
      log.debug(String.format("delete id=%s", id));
    }
    Item item = em.find(Item.class, id);
    if (item == null) {
      throw new NoResultException("Item with id=" + id + " does not exist");
    }
    em.remove(item);
    return from(item);
  }

  Item from(ItemDTO dto) {
    Item entity = new Item();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setEntityVersion(dto.getEntityVersion());
    return entity;
  }

  ItemDTO from(Item entity) {
    ItemDTO dto = new ItemDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setEntityVersion(entity.getEntityVersion());
    return dto;
  }
}
