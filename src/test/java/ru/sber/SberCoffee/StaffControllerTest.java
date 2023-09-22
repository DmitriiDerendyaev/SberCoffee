package ru.sber.SberCoffee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.SberCoffee.controller.StaffController;
import ru.sber.SberCoffee.dto.StaffRequestDTO;
import ru.sber.SberCoffee.dto.StaffResponseDTO;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.service.PositionService;
import ru.sber.SberCoffee.service.StaffService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StaffControllerTest {

    @InjectMocks
    private StaffController staffController;

    @Mock
    private StaffService staffService;

    @Mock
    private PositionService positionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff(1, "John", "Doe", "JohnDoe", new Position(1, "Manager"), "1234567890", "123 Main St"));
        staffList.add(new Staff(2, "Jane", "Smith", "JaneSmith", new Position(2, "Clerk"), "9876543210", "456 Elm St"));

        when(staffService.getAllStaff()).thenReturn(staffList);

        ResponseEntity<List<StaffResponseDTO>> responseEntity = staffController.getAllStaff();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetAllStaffEmpty() {
        List<Staff> staffList = new ArrayList<>();

        when(staffService.getAllStaff()).thenReturn(staffList);

        ResponseEntity<List<StaffResponseDTO>> responseEntity = staffController.getAllStaff();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetStaffById() {
        int staffId = 1;
        Staff staff = new Staff(staffId, "John", "Doe", "JohnDoe", new Position(1, "Manager"), "1234567890", "123 Main St");

        when(staffService.getStaffById(staffId)).thenReturn(Optional.of(staff));

        ResponseEntity<StaffResponseDTO> responseEntity = staffController.getStaffById(staffId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(staff.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void testGetStaffByIdNotFound() {
        int staffId = 1;

        when(staffService.getStaffById(staffId)).thenReturn(Optional.empty());

        ResponseEntity<StaffResponseDTO> responseEntity = staffController.getStaffById(staffId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateStaff() {
        StaffRequestDTO staffRequestDTO = new StaffRequestDTO("John", "Doe", "JohnDoe", 1, "1234567890", "123 Main St");
        Position position = new Position(1, "Manager");
        Staff createdStaff = new Staff(1, "John", "Doe", "JohnDoe", position, "1234567890", "123 Main St");

        when(positionService.getPositionById(staffRequestDTO.getPosition())).thenReturn(Optional.of(position));
        when(staffService.createStaff(any(Staff.class))).thenReturn(createdStaff);

        ResponseEntity<StaffResponseDTO> responseEntity = staffController.createStaff(staffRequestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdStaff.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void testCreateStaffBadRequest() {
        ResponseEntity<StaffResponseDTO> responseEntity = staffController.createStaff(null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateStaffInvalidPosition() {
        StaffRequestDTO staffRequestDTO = new StaffRequestDTO("John", "Doe", "JohnDoe", 999, "1234567890", "123 Main St");

        when(positionService.getPositionById(staffRequestDTO.getPosition())).thenReturn(Optional.empty());

        ResponseEntity<StaffResponseDTO> responseEntity = staffController.createStaff(staffRequestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateStaff() {
        int staffId = 1;
        StaffRequestDTO staffRequestDTO = new StaffRequestDTO("Jane", "Smith", "JaneSmith", 2, "9876543210", "456 Elm St");
        Position newPosition = new Position(2, "Clerk");
        Staff updatedStaff = new Staff(staffId, "Jane", "Smith", "JaneSmith", newPosition, "9876543210", "456 Elm St");

        when(positionService.getPositionById(staffRequestDTO.getPosition())).thenReturn(Optional.of(newPosition));
        when(staffService.updateStaff(eq(staffId), any(Staff.class))).thenReturn(updatedStaff);

        ResponseEntity<StaffResponseDTO> responseEntity = staffController.updateStaff(staffId, staffRequestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedStaff.getName(), responseEntity.getBody().getName());
    }

    @Test
    public void testUpdateStaffNotFound() {
        int staffId = 1;
        StaffRequestDTO staffRequestDTO = new StaffRequestDTO("Jane", "Smith", "JaneSmith", 2, "9876543210", "456 Elm St");

        when(positionService.getPositionById(staffRequestDTO.getPosition())).thenReturn(Optional.of(new Position(2, "Clerk")));
        when(staffService.updateStaff(eq(staffId), any(Staff.class))).thenReturn(null);

        ResponseEntity<StaffResponseDTO> responseEntity = staffController.updateStaff(staffId, staffRequestDTO);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateStaffBadRequest() {
        ResponseEntity<StaffResponseDTO> responseEntity = staffController.updateStaff(1, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateStaffInvalidPosition() {
        int staffId = 1;
        StaffRequestDTO staffRequestDTO = new StaffRequestDTO("Jane", "Smith", "JaneSmith", 999, "9876543210", "456 Elm St");

        when(positionService.getPositionById(staffRequestDTO.getPosition())).thenReturn(Optional.empty());

        ResponseEntity<StaffResponseDTO> responseEntity = staffController.updateStaff(staffId, staffRequestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteStaff() {
        int staffId = 1;

        when(staffService.deleteStaff(staffId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = staffController.deleteStaff(staffId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteStaffNotFound() {
        int staffId = 1;

        when(staffService.deleteStaff(staffId)).thenReturn(false);

        ResponseEntity<Void> responseEntity = staffController.deleteStaff(staffId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}