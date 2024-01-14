package com.punyadev.springredis.item.service;

import com.punyadev.springredis.item.model.Item;
import com.punyadev.springredis.item.repository.ItemRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Cacheable(value = "itemCache", key = "#id", unless = "#result==null")
    public Item getItemForId(Long id) {
        return itemRepository.findById(id)
                .orElse(null);
    }

    public List<Item> findAll() {
       return itemRepository.findAll();
    }

    @CachePut(value = "itemCache",key = "#id")
    public Optional<Item> update(Long id, Item item) {
        Optional<Item> opt = itemRepository.findById(id);
        if(!opt.isPresent()) {
            return Optional.empty();
        }
        item.setId(id);
        return Optional.of(itemRepository.save(item));
    }

    @CacheEvict(value = "user", allEntries = true)
    public boolean delete(Long id) {
        try {
            itemRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
