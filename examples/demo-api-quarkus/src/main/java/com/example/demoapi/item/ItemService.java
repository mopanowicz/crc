package com.example.demoapi.item;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class ItemService {

    static final Logger log = LoggerFactory.getLogger(ItemService.class);

    final EntityManager em;

    public ItemService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Item create(Item item) {
        log.debug("create item={}", item);
        em.persist(item);
        return item;
    }

    public List<Item> get() {
        log.debug("get all");
        TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item ORDER BY item.name", Item.class);
        return query.getResultList();
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    public Item get(Long id) {
        log.debug("get id={}", id);
        Item item = em.find(Item.class, id);
        if (item != null) {
            return item;
        }
        throw new NoResultException("Item with id=" + id + " does not exist");
    }

    public List<Item> get(List<Long> ids) {
        log.debug("get ids={}", ids);
        TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item WHERE item.id IN (:ids) ORDER BY name", Item.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    public Item findByName(String name) {
        TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item WHERE item.name = :name", Item.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Transactional
    public Item update(Item item) {
        log.debug("update item={}", item);
        return em.merge(item);
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    @Transactional
    public Item delete(Long id) {
        log.debug("delete id={}", id);
        Item item = em.find(Item.class, id);
        if (item == null) {
            throw new NoResultException("Item with id=" + id + " does not exist");
        }
        em.remove(item);
        return item;
    }
}
