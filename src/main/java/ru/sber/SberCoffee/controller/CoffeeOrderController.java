package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.CoffeeOrder;
import ru.sber.SberCoffee.service.CoffeeOrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CoffeeOrderController {

    private final CoffeeOrderService coffeeOrderService;

    @GetMapping
    public ResponseEntity<List<CoffeeOrder>> getAllCoffeeOrders() {
        List<CoffeeOrder> coffeeOrderList = coffeeOrderService.getAllCoffeeOrders();
        if (coffeeOrderList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(coffeeOrderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeOrder> getCoffeeOrderById(@PathVariable Long id) {
        Optional<CoffeeOrder> coffeeOrderOptional = coffeeOrderService.getCoffeeOrderById(id);

        if (coffeeOrderOptional.isPresent()) {
            return ResponseEntity.ok(coffeeOrderOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CoffeeOrder> createCoffeeOrder(@RequestBody CoffeeOrder coffeeOrder) {
        if (coffeeOrder == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(coffeeOrderService.createCoffeeOrder(coffeeOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoffeeOrder> updateCoffeeOrder(@PathVariable Long id, @RequestBody CoffeeOrder coffeeOrder) {
        CoffeeOrder updatedCoffeeOrder = coffeeOrderService.updateCoffeeOrder(id, coffeeOrder);

        if (updatedCoffeeOrder != null) {
            return ResponseEntity.ok(updatedCoffeeOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffeeOrder(@PathVariable Long id) {
        if (coffeeOrderService.deleteCoffeeOrder(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
