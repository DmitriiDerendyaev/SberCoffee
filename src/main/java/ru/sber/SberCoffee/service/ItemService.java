package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Item;

import java.util.List;
import java.util.Optional;

/**
 * The interface Item service.
 */
public interface ItemService {

    /**
     * Gets all items.
     *
     * @return the all items
     */
    List<Item> getAllItems();

    /**
     * Gets item by id.
     *
     * @param id the id
     * @return the item by id
     */
    Optional<Item> getItemById(Long id);

    /**
     * Create item item.
     *
     * @param item the item
     * @return the item
     */
    Item createItem(Item item);

    /**
     * Update item item.
     *
     * @param id   the id
     * @param item the item
     * @return the item
     */
    Item updateItem(Long id, Item item);

    /**
     * Delete item boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteItem(Long id);

    /**
     * Gets item by name.
     *
     * @param name the name
     * @return the item by name
     */
    Item getItemByName(String name);

}
