package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.repository.PositionRepo;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    List<Position> getAllPositions();
    Optional<Position> getPositionById(int id);
    Position createPosition(Position position);
    Position updatePosition(int id, Position newPosition);
    boolean deletePosition(int id);
}
