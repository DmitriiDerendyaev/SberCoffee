package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.CoffeeOrder;

import java.util.List;
import java.util.Optional;

public interface CoffeeOrderService {
    List<CoffeeOrder> getAllCoffeeOrders();

    Optional<CoffeeOrder> getCoffeeOrderById(Long id);

    CoffeeOrder createCoffeeOrder(CoffeeOrder coffeeOrder);

    CoffeeOrder updateCoffeeOrder(Long id, CoffeeOrder coffeeOrder);

    boolean deleteCoffeeOrder(Long id);
}
