package ru.sber.SberCoffee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.SberCoffee.controller.PositionController;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.service.PositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PositionControllerTest {

    @InjectMocks
    private PositionController positionController;

    @Mock
    private PositionService positionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPositions() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(1, "Barista"));
        positions.add(new Position(2, "Manager"));

        when(positionService.getAllPositions()).thenReturn(positions);

        List<Position> result = positionController.getAllPositions();

        assertEquals(positions, result);
    }

    @Test
    public void testGetPositionById() {
        int positionId = 1;
        Position position = new Position(positionId, "Barista");

        when(positionService.getPositionById(positionId)).thenReturn(Optional.of(position));

        ResponseEntity<Position> responseEntity = positionController.getPositionById(positionId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(position, responseEntity.getBody());
    }

    @Test
    public void testGetPositionByIdNotFound() {
        int positionId = 1;

        when(positionService.getPositionById(positionId)).thenReturn(Optional.empty());

        ResponseEntity<Position> responseEntity = positionController.getPositionById(positionId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreatePosition() {
        Position positionToCreate = new Position(1, "Manager");
        Position createdPosition = new Position(1, "Manager");

        when(positionService.createPosition(positionToCreate)).thenReturn(createdPosition);

        ResponseEntity<Position> responseEntity = positionController.createPosition(positionToCreate);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdPosition, responseEntity.getBody());
    }

    @Test
    public void testCreatePositionBadRequest() {
        ResponseEntity<Position> responseEntity = positionController.createPosition(null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdatePosition() {
        int positionId = 1;
        Position positionToUpdate = new Position(positionId, "Manager");
        Position updatedPosition = new Position(positionId, "Owner");

        when(positionService.updatePosition(positionId, positionToUpdate)).thenReturn(updatedPosition);

        ResponseEntity<Position> responseEntity = positionController.updatePosition(positionId, positionToUpdate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedPosition, responseEntity.getBody());
    }

    @Test
    public void testUpdatePositionNotFound() {
        int positionId = 1;
        Position positionToUpdate = new Position(positionId, "Manager");

        when(positionService.updatePosition(positionId, positionToUpdate)).thenReturn(null);

        ResponseEntity<Position> responseEntity = positionController.updatePosition(positionId, positionToUpdate);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeletePosition() {
        int positionId = 1;

        when(positionService.deletePosition(positionId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = positionController.deletePosition(positionId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeletePositionNotFound() {
        int positionId = 1;

        when(positionService.deletePosition(positionId)).thenReturn(false);

        ResponseEntity<Void> responseEntity = positionController.deletePosition(positionId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
