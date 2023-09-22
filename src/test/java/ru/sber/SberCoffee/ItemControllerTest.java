package ru.sber.SberCoffee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.SberCoffee.controller.ItemController;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.service.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "Coffee", 3.5));
        itemList.add(new Item(2, "Tea", 2.0));

        when(itemService.getAllItems()).thenReturn(itemList);

        ResponseEntity<List<Item>> responseEntity = itemController.getAllItems();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(itemList, responseEntity.getBody());
    }

    @Test
    public void testGetItemById() {
        Long itemId = 1L;
        Item item = new Item(Math.toIntExact(itemId), "Coffee", 3.5);

        when(itemService.getItemById(itemId)).thenReturn(Optional.of(item));

        ResponseEntity<Item> responseEntity = itemController.getItemById(itemId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(item, responseEntity.getBody());
    }

    @Test
    public void testGetItemByIdNotFound() {
        Long itemId = 1L;

        when(itemService.getItemById(itemId)).thenReturn(Optional.empty());

        ResponseEntity<Item> responseEntity = itemController.getItemById(itemId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetItemByName() {
        String itemName = "Coffee";
        Item item = new Item(1, itemName, 3.5);

        when(itemService.getItemByName(itemName)).thenReturn(item);

        ResponseEntity<Item> responseEntity = itemController.getItemByName(itemName);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(item, responseEntity.getBody());
    }

    @Test
    public void testGetItemByNameNotFound() {
        String itemName = "Tea";

        when(itemService.getItemByName(itemName)).thenReturn(null);

        ResponseEntity<Item> responseEntity = itemController.getItemByName(itemName);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateItem() {
        Item itemToCreate = new Item(1, "Coffee", 3.5);
        Item createdItem = new Item(1, "Coffee", 3.5);

        when(itemService.createItem(itemToCreate)).thenReturn(createdItem);

        ResponseEntity<Item> responseEntity = itemController.createItem(itemToCreate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdItem, responseEntity.getBody());
    }

    @Test
    public void testCreateItemBadRequest() {
        ResponseEntity<Item> responseEntity = itemController.createItem(null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateItem() {
        int itemId = 1;
        Item itemToUpdate = new Item(itemId, "Coffee", 3.5);
        Item updatedItem = new Item(itemId, "Espresso", 2.0);

        when(itemService.updateItem((long) itemId, itemToUpdate)).thenReturn(updatedItem);

        ResponseEntity<Item> responseEntity = itemController.updateItem((long)itemId, itemToUpdate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedItem, responseEntity.getBody());
    }

    @Test
    public void testUpdateItemNotFound() {
        int itemId = 1;
        Item itemToUpdate = new Item(itemId, "Coffee", 3.5);

        when(itemService.updateItem((long)itemId, itemToUpdate)).thenReturn(null);

        ResponseEntity<Item> responseEntity = itemController.updateItem((long)itemId, itemToUpdate);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteItem() {
        Long itemId = 1L;

        when(itemService.deleteItem(itemId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = itemController.deleteItem(itemId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteItemNotFound() {
        Long itemId = 1L;

        when(itemService.deleteItem(itemId)).thenReturn(false);

        ResponseEntity<Void> responseEntity = itemController.deleteItem(itemId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
