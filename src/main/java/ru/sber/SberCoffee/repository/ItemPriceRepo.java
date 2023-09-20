package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.entity.ItemPrice;

import java.util.List;

public interface ItemPriceRepo extends JpaRepository<ItemPrice, Long> {
    public List<ItemPrice> findItemPriceByItem(Item item);
}
