package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.CoffeeOrder;

import java.util.List;
import java.util.Optional;

/**
 * The interface Coffee order service.
 */
public interface CoffeeOrderService {
    /**
     * Gets all coffee orders.
     *
     * @return the all coffee orders
     */
    List<CoffeeOrder> getAllCoffeeOrders();

    /**
     * Gets coffee order by id.
     *
     * @param id the id
     * @return the coffee order by id
     */
    Optional<CoffeeOrder> getCoffeeOrderById(Long id);

    /**
     * Create coffee order coffee order.
     *
     * @param coffeeOrder the coffee order
     * @return the coffee order
     */
    CoffeeOrder createCoffeeOrder(CoffeeOrder coffeeOrder);

    /**
     * Update coffee order coffee order.
     *
     * @param id          the id
     * @param coffeeOrder the coffee order
     * @return the coffee order
     */
    CoffeeOrder updateCoffeeOrder(Long id, CoffeeOrder coffeeOrder);

    /**
     * Delete coffee order boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteCoffeeOrder(Long id);
}
