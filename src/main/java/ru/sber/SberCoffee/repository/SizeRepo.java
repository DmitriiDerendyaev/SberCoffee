package ru.sber.SberCoffee.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Size;

public interface SizeRepo extends JpaRepository<Size, Integer> {
}
