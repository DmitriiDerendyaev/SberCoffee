package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.CoffeeOrder;

public interface OrderRepo extends JpaRepository<CoffeeOrder, Long> {
}
