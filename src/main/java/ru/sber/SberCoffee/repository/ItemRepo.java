package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Item;

/**
 * The interface Item repo.
 */
public interface ItemRepo extends JpaRepository<Item, Long> {
    /**
     * Find by name item.
     *
     * @param name the name
     * @return the item
     */
    Item findByName(String name);
}
