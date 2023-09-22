package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.repository.PositionRepo;

import java.util.List;
import java.util.Optional;

/**
 * The interface Position service.
 */
public interface PositionService {
    /**
     * Gets all positions.
     *
     * @return the all positions
     */
    List<Position> getAllPositions();

    /**
     * Gets position by id.
     *
     * @param id the id
     * @return the position by id
     */
    Optional<Position> getPositionById(int id);

    /**
     * Create position position.
     *
     * @param position the position
     * @return the position
     */
    Position createPosition(Position position);

    /**
     * Update position position.
     *
     * @param id          the id
     * @param newPosition the new position
     * @return the position
     */
    Position updatePosition(int id, Position newPosition);

    /**
     * Delete position boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deletePosition(int id);
}
