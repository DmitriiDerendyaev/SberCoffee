package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Status;

/**
 * The interface Status repo.
 */
public interface StatusRepo extends JpaRepository<Status, Integer> {
}
