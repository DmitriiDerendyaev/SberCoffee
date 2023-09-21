package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.dto.CoffeeOrderRequestDTO;
import ru.sber.SberCoffee.dto.CoffeeOrderResponseDTO;
import ru.sber.SberCoffee.dto.StaffResponseDTO;
import ru.sber.SberCoffee.entity.*;
import ru.sber.SberCoffee.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CoffeeOrderController {

    private final CoffeeOrderService coffeeOrderService;
    private final ClientService clientService;
    private final ItemService itemService;
    private final StatusService statusService;
    private final StaffService staffService;


    @PostMapping
    public ResponseEntity<CoffeeOrderResponseDTO> createCoffeeOrder(@RequestBody CoffeeOrderRequestDTO requestDTO) {
        if (requestDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        CoffeeOrder coffeeOrder = new CoffeeOrder();
        coffeeOrder.setClient(clientService.getClientById(requestDTO.getClient()).orElse(null));
        coffeeOrder.setItem(itemService.getItemById(requestDTO.getItem()).orElse(null));
        coffeeOrder.setQuantity(requestDTO.getQuantity());
        coffeeOrder.setStatus(statusService.getStatusById(requestDTO.getStatus()).orElse(null));
        coffeeOrder.setStaff(staffService.getStaffById(requestDTO.getStatus()).orElse(null));

        coffeeOrder.setStartTime(LocalDateTime.now());
        double price = coffeeOrder.getItem().getPrice();
        int quantity = coffeeOrder.getQuantity();
        BigDecimal total = BigDecimal.valueOf(price * quantity);
        coffeeOrder.setTotal(total);

        CoffeeOrder createdCoffeeOrder = coffeeOrderService.createCoffeeOrder(coffeeOrder);
        CoffeeOrderResponseDTO responseDTO = convertToResponseDTO(createdCoffeeOrder);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CoffeeOrderResponseDTO>> getAllCoffeeOrders() {
        List<CoffeeOrder> coffeeOrderList = coffeeOrderService.getAllCoffeeOrders();
        if (coffeeOrderList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Преобразуем список заказов в список DTO
        List<CoffeeOrderResponseDTO> responseDTOList = coffeeOrderList.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeOrderResponseDTO> getCoffeeOrderById(@PathVariable Long id) {
        Optional<CoffeeOrder> coffeeOrderOptional = coffeeOrderService.getCoffeeOrderById(id);

        if (coffeeOrderOptional.isPresent()) {
            CoffeeOrderResponseDTO responseDTO = convertToResponseDTO(coffeeOrderOptional.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoffeeOrderResponseDTO> updateCoffeeOrder(@PathVariable Long id, @RequestBody CoffeeOrderRequestDTO requestDTO) {
        if (requestDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        CoffeeOrder coffeeOrder = new CoffeeOrder();
        coffeeOrder.setClient(clientService.getClientById(requestDTO.getClient()).orElse(null));
        coffeeOrder.setItem(itemService.getItemById(requestDTO.getItem()).orElse(null));
        coffeeOrder.setQuantity(requestDTO.getQuantity());
        coffeeOrder.setStatus(statusService.getStatusById(requestDTO.getStatus()).orElse(null));
        coffeeOrder.setStaff(staffService.getStaffById(requestDTO.getStaff()).orElse(null));


        // Вычисляем startTime и total
        coffeeOrder.setStartTime(LocalDateTime.now());
        double price = coffeeOrder.getItem().getPrice();
        int quantity = coffeeOrder.getQuantity();
        BigDecimal total = BigDecimal.valueOf(price * quantity);
        coffeeOrder.setTotal(total);

        // Обновляем заказ и преобразуем его в DTO
        CoffeeOrder updatedCoffeeOrder = coffeeOrderService.updateCoffeeOrder(id, coffeeOrder);
        if (updatedCoffeeOrder != null) {
            CoffeeOrderResponseDTO responseDTO = convertToResponseDTO(updatedCoffeeOrder);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffeeOrder(@PathVariable Long id) {
        if (coffeeOrderService.deleteCoffeeOrder(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private CoffeeOrderResponseDTO convertToResponseDTO(CoffeeOrder coffeeOrder) {
        CoffeeOrderResponseDTO responseDTO = new CoffeeOrderResponseDTO();
        responseDTO.setId(coffeeOrder.getId());
        responseDTO.setClient(coffeeOrder.getClient()); // Пример, предполагается, что у вас есть методы доступа к полям в CoffeeOrder
        responseDTO.setItem(coffeeOrder.getItem()); // Пример
        responseDTO.setQuantity(coffeeOrder.getQuantity()); // Пример
        responseDTO.setStatus(coffeeOrder.getStatus()); // Пример
        responseDTO.setStaff(mapStaffToStaffDTO(coffeeOrder.getStaff())); // Пример
        responseDTO.setStartTime(coffeeOrder.getStartTime()); // Пример
        responseDTO.setTotal(coffeeOrder.getTotal()); // Пример
        return responseDTO;
    }

    private StaffResponseDTO mapStaffToStaffDTO(Staff staff) {
        return new StaffResponseDTO(
                staff.getId(),
                staff.getName(),
                staff.getSurname(),
                staff.getPatronymic(),
                staff.getPosition().getName(),
                staff.getPhoneNumber(),
                staff.getAddress()
        );
    }
}
