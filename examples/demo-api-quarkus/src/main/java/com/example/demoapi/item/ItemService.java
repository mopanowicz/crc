package com.example.demoapi.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ItemService {

    static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Inject
    EntityManager em;

    @Transactional
    public Item create(Item item) {
        log.debug("create item={}", item);
        em.persist(item);
        return item;
    }

    @Transactional
    public List<Item> get() {
        log.debug("get all");
        TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item ORDER BY item.name", Item.class);
        return query.getResultList();
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    @Transactional
    public Item get(Long id) {
        log.debug("get id={}", id);
        Item item = em.find(Item.class, id);
        if (item != null) {
            return item;
        }
        throw new NoResultException("Item with id=" + id + " does not exist");
    }

    @Transactional
    public List<Item> get(List<Long> ids) {
        log.debug("get ids={}", ids);
        TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item WHERE item.id IN (:ids) ORDER BY name", Item.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    /**
     * @throws NoResultException if the item does not exist
     */
    @Transactional
    public Item findByName(String name) {
        TypedQuery<Item> query = em.createQuery("SELECT item FROM Item item WHERE item.name = :name", Item.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Transactional
    public Item update(Item item) {
        log.debug("update item={}", item);
        em.merge(item);
        return item;
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
