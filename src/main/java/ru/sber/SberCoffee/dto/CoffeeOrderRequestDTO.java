package ru.sber.SberCoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoffeeOrderRequestDTO {
    private Long client;
    private Long item;
    private int quantity;
    private int status;
    private int staff;
}
