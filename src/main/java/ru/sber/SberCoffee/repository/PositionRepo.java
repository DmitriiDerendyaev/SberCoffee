package ru.sber.SberCoffee.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.SberCoffee.entity.Position;

/**
 * The interface Position repo.
 */
public interface PositionRepo extends JpaRepository<Position, Integer> {

}
