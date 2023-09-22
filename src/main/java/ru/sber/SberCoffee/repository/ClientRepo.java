package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Client;

/**
 * The interface Client repo.
 */
public interface ClientRepo extends JpaRepository<Client, Long> {
}
