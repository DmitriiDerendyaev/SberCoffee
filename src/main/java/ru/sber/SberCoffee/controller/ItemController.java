package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.service.ItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

        private final ItemService itemService;

        @GetMapping
        public ResponseEntity<List<Item>> getAllItems() {
                List<Item> itemList = itemService.getAllItems();
                if (itemList.isEmpty()) {
                        return ResponseEntity.notFound().build();
                }

                return ResponseEntity.ok(itemList);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Item> getItemById(@PathVariable Long id) {
                Optional<Item> itemOptional = itemService.getItemById(id);

                if (itemOptional.isPresent()) {
                        return ResponseEntity.ok(itemOptional.get());
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/search")
        public ResponseEntity<Item> getItemByName(@RequestParam String name) {
                Item item = itemService.getItemByName(name);

                if (item != null) {
                        return ResponseEntity.ok(item);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        @PostMapping
        public ResponseEntity<Item> createItem(@RequestBody Item item) {
                if (item == null) {
                        return ResponseEntity.badRequest().build();
                }

                return ResponseEntity.ok(itemService.createItem(item));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
                Item updatedItem = itemService.updateItem(id, item);

                if (updatedItem != null) {
                        return ResponseEntity.ok(updatedItem);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
                if (itemService.deleteItem(id)) {
                        return ResponseEntity.noContent().build();
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

}
