package com.example.demo.service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Service
public class ItemService {

    final static Logger log = LoggerFactory.getLogger("ItemService");

    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDTO create(ItemDTO dto) {
        if (log.isDebugEnabled()) {
            log.debug("create dto="+ dto);
        }
        Item item = from(dto);
        return from(itemRepository.save(item));
    }

    public List<ItemDTO> get() {
        if (log.isDebugEnabled()) {
            log.debug("get");
        }
        List<ItemDTO> ps = new Vector<>();
        for (Item item : itemRepository.findByOrderByNameAsc()) {
            ps.add(from(item));
        }
        return ps;
    }

    public ItemDTO get(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("get it="+ id);
        }
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return from(item.get());
        } else {
            throw new NotFoundException("ItemDTO id="+ id +" not found");
        }
    }

    public List<ItemDTO> get(List<Long> ids) {
        if (log.isDebugEnabled()) {
            log.debug("get ids="+ ids);
        }
        List<ItemDTO> dtos = new Vector<>();
        for (Item item : itemRepository.findByIdIn(ids)) {
            dtos.add(from(item));
        }
        return dtos;
    }

    public ItemDTO update(ItemDTO dto) {
        if (log.isDebugEnabled()) {
            log.debug("update dto="+ dto);
        }
        Item item = from(dto);
        return from(itemRepository.save(item));
    }

    public ItemDTO delete(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete id="+ id);
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
