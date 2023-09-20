package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
