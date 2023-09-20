package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.dto.ItemDTO;
import ru.sber.SberCoffee.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItems();

    Item getItemByName(String name);

    ItemDTO getItemDTOByName(String name);

    void save(Item item);
}
