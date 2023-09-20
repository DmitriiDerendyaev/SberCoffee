package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
