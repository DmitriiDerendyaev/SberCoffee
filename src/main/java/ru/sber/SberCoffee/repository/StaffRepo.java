package ru.sber.SberCoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Staff;

/**
 * The interface Staff repo.
 */
public interface StaffRepo extends JpaRepository<Staff, Integer> {
}
