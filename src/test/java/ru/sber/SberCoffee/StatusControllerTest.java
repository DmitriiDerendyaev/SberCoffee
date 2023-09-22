package ru.sber.SberCoffee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.SberCoffee.controller.StatusController;
import ru.sber.SberCoffee.entity.Status;
import ru.sber.SberCoffee.service.StatusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatusControllerTest {
    @InjectMocks
    private StatusController statusController;

    @Mock
    private StatusService statusService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStatus() {
        List<Status> statusList = new ArrayList<>();
        statusList.add(new Status(1, "Active"));
        statusList.add(new Status(2, "Inactive"));

        when(statusService.getAllStatus()).thenReturn(statusList);

        ResponseEntity<List<Status>> responseEntity = statusController.getAllStatus();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(statusList, responseEntity.getBody());
    }

    @Test
    public void testGetStatusById() {
        int statusId = 1;
        Status status = new Status(statusId, "Active");

        when(statusService.getStatusById(statusId)).thenReturn(Optional.of(status));

        ResponseEntity<Status> responseEntity = statusController.getStatusById(statusId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(status, responseEntity.getBody());
    }

    @Test
    public void testGetStatusByIdNotFound() {
        int statusId = 1;

        when(statusService.getStatusById(statusId)).thenReturn(Optional.empty());

        ResponseEntity<Status> responseEntity = statusController.getStatusById(statusId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateStatus() {
        Status statusToCreate = new Status(1, "Active");
        Status createdStatus = new Status(1, "Active");

        when(statusService.createStatus(statusToCreate)).thenReturn(createdStatus);

        ResponseEntity<Status> responseEntity = statusController.createStatus(statusToCreate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdStatus, responseEntity.getBody());
    }

    @Test
    public void testCreateStatusBadRequest() {
        ResponseEntity<Status> responseEntity = statusController.createStatus(null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateStatus() {
        int statusId = 1;
        Status statusToUpdate = new Status(statusId, "Inactive");
        Status updatedStatus = new Status(statusId, "Active");

        when(statusService.updateStatus(statusId, statusToUpdate)).thenReturn(updatedStatus);

        ResponseEntity<Status> responseEntity = statusController.updateStatus(statusId, statusToUpdate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedStatus, responseEntity.getBody());
    }

    @Test
    public void testUpdateStatusNotFound() {
        int statusId = 1;
        Status statusToUpdate = new Status(statusId, "Inactive");

        when(statusService.updateStatus(statusId, statusToUpdate)).thenReturn(null);

        ResponseEntity<Status> responseEntity = statusController.updateStatus(statusId, statusToUpdate);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteStatus() {
        int statusId = 1;

        when(statusService.deleteStatus(statusId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = statusController.deleteStatus(statusId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteStatusNotFound() {
        int statusId = 1;

        when(statusService.deleteStatus(statusId)).thenReturn(false);

        ResponseEntity<Void> responseEntity = statusController.deleteStatus(statusId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
