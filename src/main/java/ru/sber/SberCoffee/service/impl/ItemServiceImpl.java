package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.dto.ItemDTO;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.entity.ItemPrice;
import ru.sber.SberCoffee.entity.Size;
import ru.sber.SberCoffee.repository.ItemPriceRepo;
import ru.sber.SberCoffee.repository.ItemRepo;
import ru.sber.SberCoffee.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    ItemRepo itemRepo;
    ItemPriceRepo itemPrice;

    @Override
    public List<Item> getItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item getItemByName(String name) {
        return itemRepo.findByName(name);
    }

    public ItemDTO getItemDTOByName(String itemName) {
        Item item = itemRepo.findByName(itemName);
        if (item == null) {
            return null; // Обработка ошибки, если товар не найден
        }

        return createItemDTO(item);
    }


    /////////
    @Override
    public void save(Item item) {
        itemRepo.save(item);
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        List<ItemDTO> itemDTOs = new ArrayList<>();

        for (Item item : items) {
            itemDTOs.add(createItemDTO(item));
        }

        return itemDTOs;
    }

    private ItemDTO createItemDTO(Item item) {
        List<ItemPrice> itemPrices = itemPrice.findItemPriceByItem(item);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setItem_id(item.getId());
        itemDTO.setBasePrice((int) item.getBasePrice());

        List<Long> sizeIds = new ArrayList<>();
        List<Long> prices = new ArrayList<>();
        for (ItemPrice itemPrice : itemPrices) {
            Size size = itemPrice.getSize();
            sizeIds.add((long) size.getId());
            prices.add((long) size.getPriceModifier());
        }

        itemDTO.setSizes(sizeIds);
        itemDTO.setModifiers(prices);

        return itemDTO;
    }


}
