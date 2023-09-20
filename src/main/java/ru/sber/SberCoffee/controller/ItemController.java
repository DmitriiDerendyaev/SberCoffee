package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

        ItemService itemService;

        @GetMapping()
        public List<Item> getItems(@RequestParam(required = false) String name){
                return name.isEmpty() ? itemService.getItems() : List.of(itemService.getItemByName(name));
        }

//        @PostMapping()
//        public void save
}
