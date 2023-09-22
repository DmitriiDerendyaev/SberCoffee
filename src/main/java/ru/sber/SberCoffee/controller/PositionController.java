package ru.sber.SberCoffee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.service.PositionService;

import java.util.List;
import java.util.Optional;

/**
 * The type Position controller.
 */
@Slf4j
@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    /**
     * Gets all positions.
     *
     * @return the all positions
     */
    @GetMapping
    public List<Position> getAllPositions() {
        List<Position> positions = positionService.getAllPositions();
        if (positions.isEmpty()) {
            log.warn("List of positions is empty");
        } else {
            log.info("Retrieved {} positions", positions.size());
        }
        return positions;
    }

    /**
     * Gets position by id.
     *
     * @param id the id
     * @return the position by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable int id) {
        Optional<Position> position = positionService.getPositionById(id);
        if (position.isPresent()) {
            log.info("Retrieved position by ID: {}", id);
            return ResponseEntity.ok(position.get());
        } else {
            log.warn("Position with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create position response entity.
     *
     * @param position the position
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Position> createPosition(@Valid @RequestBody Position position) {
        if (position == null) {
            log.warn("Sent body is null");
            return ResponseEntity.badRequest().build();
        }

        Position createdPosition = positionService.createPosition(position);
        if (createdPosition != null) {
            log.info("Position created successfully: {}", createdPosition);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPosition);
        } else {
            log.error("Failed to create position");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Update position response entity.
     *
     * @param id       the id
     * @param position the position
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable int id, @Valid @RequestBody Position position) {
        Position updatedPosition = positionService.updatePosition(id, position);
        if (updatedPosition != null) {
            log.info("Position updated successfully: {}", updatedPosition);
            return ResponseEntity.ok(updatedPosition);
        } else {
            log.warn("Position with ID {} not found. Update failed.", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete position response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable int id) {
        boolean deleted = positionService.deletePosition(id);
        if (deleted) {
            log.info("Position with ID {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Position with ID {} not found. Deletion failed.", id);
            return ResponseEntity.notFound().build();
        }
    }
}
