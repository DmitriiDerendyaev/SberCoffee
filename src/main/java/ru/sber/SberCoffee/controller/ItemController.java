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

/**
 * The type Item controller.
 */
@Slf4j
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

        private final ItemService itemService;

    /**
     * Gets all items.
     *
     * @return the all items
     */
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

    /**
     * Gets item by id.
     *
     * @param id the id
     * @return the item by id
     */
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

    /**
     * Gets item by name.
     *
     * @param name the name
     * @return the item by name
     */
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

    /**
     * Create item response entity.
     *
     * @param item the item
     * @return the response entity
     */
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

    /**
     * Update item response entity.
     *
     * @param id   the id
     * @param item the item
     * @return the response entity
     */
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

    /**
     * Delete item response entity.
     *
     * @param id the id
     * @return the response entity
     */
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
