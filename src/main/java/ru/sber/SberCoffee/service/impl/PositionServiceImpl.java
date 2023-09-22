package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.Pointcut;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.repository.PositionRepo;
import ru.sber.SberCoffee.service.PositionService;

import java.util.List;
import java.util.Optional;

/**
 * The type Position service.
 */
@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepo positionRepo;

    @Override
    public List<Position> getAllPositions() {
        return positionRepo.findAll();
    }

    @Override
    public Optional<Position> getPositionById(int id) {
        return positionRepo.findById(id);
    }

    @Override
    public Position createPosition(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public Position updatePosition(int id, Position newPosition) {
        if (positionRepo.existsById(id)) {
            newPosition.setId(id);
            return positionRepo.save(newPosition);
        } else {
            return null; // Возвращаем null, если позиция не найдена
        }
    }

    @Override
    public boolean deletePosition(int id) {
        if (positionRepo.existsById(id)) {
            positionRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}