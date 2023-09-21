package ru.sber.SberCoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoffeeOrderRequestDTO {
    private Long clientId;
    private Long itemId;
    private int quantity;
    private Long statusId;
    private Long staffId;
}
