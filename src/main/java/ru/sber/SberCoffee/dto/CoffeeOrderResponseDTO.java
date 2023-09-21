package ru.sber.SberCoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeOrderResponseDTO {
    private Long id;
    private Client client;
    private Item item;
    private int quantity;
    private Status status;
    private StaffResponseDTO staff;
    private LocalDateTime startTime;
    private BigDecimal total;

}
