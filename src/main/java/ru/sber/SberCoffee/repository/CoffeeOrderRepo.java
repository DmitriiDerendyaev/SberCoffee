package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.CoffeeOrder;

/**
 * The interface Coffee order repo.
 */
public interface CoffeeOrderRepo extends JpaRepository<CoffeeOrder, Long> {
}
