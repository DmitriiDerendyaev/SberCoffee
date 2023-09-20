package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.repository.ItemRepo;
import ru.sber.SberCoffee.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    ItemRepo itemRepo;

    @Override
    public List<Item> getItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item getItemByName(String name) {
        return itemRepo.findByName(name);
    }

    @Override
    public void save(Item item) {
        itemRepo.save(item);
    }

}
