package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.repository.ItemRepo;
import ru.sber.SberCoffee.service.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemRepo.findById(id);
    }

    @Override
    public Item createItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item updateItem(Long id, Item item) {
        if (itemRepo.existsById(id)) {
            item.setId(Integer.parseInt(String.valueOf(id)));
            return itemRepo.save(item);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteItem(Long id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Item getItemByName(String name) {
        return itemRepo.findByName(name);
    }
}
