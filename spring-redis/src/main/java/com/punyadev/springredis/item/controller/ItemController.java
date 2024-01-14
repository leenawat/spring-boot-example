package com.punyadev.springredis.item.controller;

import com.punyadev.springredis.item.model.Item;
import com.punyadev.springredis.item.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public Item save(@RequestBody Item item) {
        return itemService.save(item);
    }

    @GetMapping()
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItemForId(id);
    }

    @PutMapping("/{id}")
    public Optional<Item> update(@PathVariable Long id, @RequestBody Item item) {
        return itemService.update(id, item);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return itemService.delete(id);
    }
}
