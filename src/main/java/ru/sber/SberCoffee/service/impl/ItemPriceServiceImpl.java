package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.ItemPrice;
import ru.sber.SberCoffee.repository.ItemPriceRepo;
import ru.sber.SberCoffee.service.ItemPriceService;

@Service
@RequiredArgsConstructor
public class ItemPriceServiceImpl implements ItemPriceService {

    private final ItemPriceRepo itemPriceRepo;

    @Override
    public void save(ItemPrice itemPrice) {
        itemPriceRepo.save(itemPrice);
    }
}
