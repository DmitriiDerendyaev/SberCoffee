package ru.sber.SberCoffee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.service.ItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

        private final ItemService itemService;

        @GetMapping
        public ResponseEntity<List<Item>> getAllItems() {
                List<Item> itemList = itemService.getAllItems();
                if (itemList.isEmpty()) {
                        log.warn("List of items is empty");
                        return ResponseEntity.notFound().build();
                }

                log.info("Retrieved {} items successfully", itemList.size());
                return ResponseEntity.ok(itemList);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Item> getItemById(@PathVariable Long id) {
                Optional<Item> itemOptional = itemService.getItemById(id);

                if (itemOptional.isPresent()) {
                        log.info("Retrieved item with ID {}", id);
                        return ResponseEntity.ok(itemOptional.get());
                } else {
                        log.warn("Item with ID {} not found", id);
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/search")
        public ResponseEntity<Item> getItemByName(@RequestParam String name) {
                Item item = itemService.getItemByName(name);

                if (item != null) {
                        log.info("Retrieved item by name: {}", name);
                        return ResponseEntity.ok(item);
                } else {
                        log.warn("Item with name '{}' not found", name);
                        return ResponseEntity.notFound().build();
                }
        }

        @PostMapping
        public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
                if (item == null) {
                        log.warn("Received request with null item");
                        return ResponseEntity.badRequest().build();
                }

                Item createdItem = itemService.createItem(item);
                log.info("Created item with ID {}", createdItem.getId());
                return ResponseEntity.ok(createdItem);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Item> updateItem(@PathVariable Long id, @Valid @RequestBody Item item) {
                Item updatedItem = itemService.updateItem(id, item);

                if (updatedItem != null) {
                        log.info("Updated item with ID {}", id);
                        return ResponseEntity.ok(updatedItem);
                } else {
                        log.warn("Item with ID {} not found for update", id);
                        return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
                if (itemService.deleteItem(id)) {
                        log.info("Deleted item with ID {}", id);
                        return ResponseEntity.noContent().build();
                } else {
                        log.warn("Item with ID {} not found for deletion", id);
                        return ResponseEntity.notFound().build();
                }
        }

}
