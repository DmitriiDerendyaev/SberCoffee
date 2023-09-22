package ru.sber.SberCoffee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sber.SberCoffee.controller.CoffeeOrderController;
import ru.sber.SberCoffee.dto.CoffeeOrderRequestDTO;
import ru.sber.SberCoffee.dto.CoffeeOrderResponseDTO;
import ru.sber.SberCoffee.entity.*;
import ru.sber.SberCoffee.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoffeeOrderControllerTest {

    @InjectMocks
    private CoffeeOrderController coffeeOrderController;

    @Mock
    private CoffeeOrderService coffeeOrderService;

    @Mock
    private ClientService clientService;

    @Mock
    private ItemService itemService;

    @Mock
    private StatusService statusService;

    @Mock
    private StaffService staffService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCoffeeOrder() {
        CoffeeOrderRequestDTO requestDTO = new CoffeeOrderRequestDTO(1L, 2L, 3, 4, 5);
        Client client = new Client(1L, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", null);
        Item item = new Item(2, "Coffee", 2.5);
        Status status = new Status(3, "Completed");
        Position position = new Position(5, "Bar");
        Staff staff = new Staff(4, "Jane", "Smith", "JaneSmith", position, "9876543210", "456 Elm St");
        CoffeeOrder createdOrder = new CoffeeOrder(1L, client, item, 3, status, staff, LocalDateTime.now(), BigDecimal.valueOf(7.5));

        when(clientService.getClientById(requestDTO.getClient())).thenReturn(Optional.of(client));
        when(itemService.getItemById(requestDTO.getItem())).thenReturn(Optional.of(item));
        when(statusService.getStatusById(requestDTO.getStatus())).thenReturn(Optional.of(status));
        when(staffService.getStaffById(requestDTO.getStaff())).thenReturn(Optional.of(staff));
        when(coffeeOrderService.createCoffeeOrder(any(CoffeeOrder.class))).thenReturn(createdOrder);

        ResponseEntity<CoffeeOrderResponseDTO> responseEntity = coffeeOrderController.createCoffeeOrder(requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdOrder.getId(), responseEntity.getBody().getId());
    }


    @Test
    public void testGetAllCoffeeOrdersEmpty() {
        List<CoffeeOrder> coffeeOrderList = new ArrayList<>();

        when(coffeeOrderService.getAllCoffeeOrders()).thenReturn(coffeeOrderList);

        ResponseEntity<List<CoffeeOrderResponseDTO>> responseEntity = coffeeOrderController.getAllCoffeeOrders();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCoffeeOrderByIdNotFound() {
        Long orderId = 1L;

        when(coffeeOrderService.getCoffeeOrderById(orderId)).thenReturn(Optional.empty());

        ResponseEntity<CoffeeOrderResponseDTO> responseEntity = coffeeOrderController.getCoffeeOrderById(orderId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateCoffeeOrder() {
        Long orderId = 1L;
        CoffeeOrderRequestDTO requestDTO = new CoffeeOrderRequestDTO(1L, 2L, 3, 4, 5);
        Client client = new Client(1L, "John", "Doe", "JohnDoe", "1234567890", "123 Main St", "john@example.com", null);
        Item item = new Item(2, "Coffee", 2.5);
        Status status = new Status(3, "Completed");
        Position position = new Position(5, "Bar");
        Staff staff = new Staff(4, "Jane", "Smith", "JaneSmith", position, "9876543210", "456 Elm St");
        CoffeeOrder updatedOrder = new CoffeeOrder(orderId, client, item, 3, status, staff, LocalDateTime.now(), BigDecimal.valueOf(7.5));

        when(clientService.getClientById(requestDTO.getClient())).thenReturn(Optional.of(client));
        when(itemService.getItemById(requestDTO.getItem())).thenReturn(Optional.of(item));
        when(statusService.getStatusById(requestDTO.getStatus())).thenReturn(Optional.of(status));
        when(staffService.getStaffById(requestDTO.getStaff())).thenReturn(Optional.of(staff));
        when(coffeeOrderService.updateCoffeeOrder(eq(orderId), any(CoffeeOrder.class))).thenReturn(updatedOrder);

        ResponseEntity<CoffeeOrderResponseDTO> responseEntity = coffeeOrderController.updateCoffeeOrder(orderId, requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(orderId, responseEntity.getBody().getId());
    }

    @Test
    public void testDeleteCoffeeOrder() {
        Long orderId = 1L;

        when(coffeeOrderService.deleteCoffeeOrder(orderId)).thenReturn(true);

        ResponseEntity<Void> responseEntity = coffeeOrderController.deleteCoffeeOrder(orderId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteCoffeeOrderNotFound() {
        Long orderId = 1L;

        when(coffeeOrderService.deleteCoffeeOrder(orderId)).thenReturn(false);

        ResponseEntity<Void> responseEntity = coffeeOrderController.deleteCoffeeOrder(orderId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
