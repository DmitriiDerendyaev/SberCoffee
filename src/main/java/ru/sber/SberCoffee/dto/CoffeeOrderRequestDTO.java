package ru.sber.SberCoffee.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoffeeOrderRequestDTO {
    @NotNull(message = "Client ID cannot be null")
    private Long client;

    @NotNull(message = "Item ID cannot be null")
    private Long item;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    private int status;
    private int staff;
}
