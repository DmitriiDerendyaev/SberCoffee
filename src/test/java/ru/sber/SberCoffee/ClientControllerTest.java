package ru.sber.SberCoffee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.SberCoffee.controller.ClientController;
import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.service.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllClient() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1)));
        clients.add(new Client(2L, "Jane", "Smith", "JaneSmith", "9876543210", "456 Elm St", "jane@example.com", LocalDate.of(1985, 5, 15)));

        when(clientService.getAllClient()).thenReturn(clients);

        List<Client> result = clientController.getAllClient().getBody();

        assertEquals(clients, result);
    }

    @Test
    public void testGetClientById() {
        Long clientId = 1L;
        Client client = new Client(clientId, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));

        when(clientService.getClientById(clientId)).thenReturn(Optional.of(client));

        ResponseEntity<Client> responseEntity = clientController.getClientById(clientId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(client, responseEntity.getBody());
    }

    @Test
    public void testGetClientByIdNotFound() {
        Long clientId = 1L;

        when(clientService.getClientById(clientId)).thenReturn(Optional.empty());

        ResponseEntity<Client> responseEntity = clientController.getClientById(clientId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateClient() {
        Client clientToCreate = new Client(null, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));
        Client createdClient = new Client(1L, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));

        when(clientService.createClient(clientToCreate)).thenReturn(createdClient);

        ResponseEntity<Client> responseEntity = clientController.createClient(clientToCreate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdClient, responseEntity.getBody());
    }

    @Test
    public void testCreateClientBadRequest() {
        ResponseEntity<Client> responseEntity = clientController.createClient(null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateClient() {
        Long clientId = 1L;
        Client clientToUpdate = new Client(clientId, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));
        Client updatedClient = new Client(clientId, "Jane", "Smith", "JaneSmith", "9876543210", "456 Elm St", "jane@example.com", LocalDate.of(1985, 5, 15));

        when(clientService.updateClient(clientId, clientToUpdate)).thenReturn(updatedClient);

        ResponseEntity<Client> responseEntity = clientController.updateClient(clientId, clientToUpdate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedClient, responseEntity.getBody());
    }

    @Test
    public void testUpdateClientNotFound() {
        Long clientId = 1L;
        Client clientToUpdate = new Client(clientId, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));

        when(clientService.updateClient(clientId, clientToUpdate)).thenReturn(null);

        ResponseEntity<Client> responseEntity = clientController.updateClient(clientId, clientToUpdate);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteClient() {
        Long clientId = 1L;

        when(clientService.deleteClient(clientId)).thenReturn(true);

        ResponseEntity<Client> responseEntity = clientController.deleteClient(clientId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteClientNotFound() {
        Long clientId = 1L;

        when(clientService.deleteClient(clientId)).thenReturn(false);

        ResponseEntity<Client> responseEntity = clientController.deleteClient(clientId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
