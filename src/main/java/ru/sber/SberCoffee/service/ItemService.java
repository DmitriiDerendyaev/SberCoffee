package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllItems();
    Optional<Item> getItemById(Long id);
    Item createItem(Item item);
    Item updateItem(Long id, Item item);
    boolean deleteItem(Long id);

    Item getItemByName(String name);

}
